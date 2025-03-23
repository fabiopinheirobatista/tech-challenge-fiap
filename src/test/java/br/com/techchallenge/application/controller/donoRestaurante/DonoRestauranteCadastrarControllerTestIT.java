package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteCadastrarRequestDTO;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class DonoRestauranteCadastrarControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DonoRestauranteRepository donoRestauranteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        donoRestauranteRepository.deleteAll();
    }

    @Test
    void deveRetornar201EDadosDoDono_QuandoCadastroValido() throws Exception {
        // Arrange
        DonoRestauranteCadastrarRequestDTO request = new DonoRestauranteCadastrarRequestDTO(
                null,
                "Ana Costa",
                new Endereco("Av. Brasil", "1500", "Sala 301", "Jardins", "São Paulo", "SP", "01414-000"),
                "ana.costa@example.com",
                "ana.costa",
                "Senha@123"
        );

        // Act & Assert
        mockMvc.perform(post("/api/donos-restaurante/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

    }

    @Test
    void deveRetornar409_QuandoEmailOuLoginJaExistem() throws Exception {
        // Arrange: Primeiro cadastro
        DonoRestauranteCadastrarRequestDTO request1 = new DonoRestauranteCadastrarRequestDTO(
                null,
                "Carlos Oliveira",
                new Endereco("Rua das Palmeiras", "45", null, "Centro", "Rio de Janeiro", "RJ", "20040-000"),
                "carlos.oliveira@example.com",
                "carlos.oliveira",
                "Senha@456"
        );
        mockMvc.perform(post("/api/donos-restaurante/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request1)));

        // Act & Assert: Segundo cadastro com mesmo email/login
        DonoRestauranteCadastrarRequestDTO request2 = new DonoRestauranteCadastrarRequestDTO(
                null,
                "Carlos Oliveira",
                new Endereco("Rua das Palmeiras", "45", null, "Centro", "Rio de Janeiro", "RJ", "20040-000"),
                "carlos.oliveira@example.com", // Email duplicado
                "carlos.oliveira", // Login duplicado
                "Senha@456"
        );

        mockMvc.perform(post("/api/donos-restaurante/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request2)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Cadastro não realizado pois já existe um registro com esse email/login!"));
    }
}