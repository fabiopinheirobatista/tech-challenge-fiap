package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class RestauranteCadastrarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private DonoRestauranteRepository donoRestauranteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Long validDonoId;
    private Long invalidDonoId = 999L;

    @BeforeEach
    void setUp() {
        restauranteRepository.deleteAll();
        donoRestauranteRepository.deleteAll();

        DonoRestauranteEntity dono = new DonoRestauranteEntity();
        dono.setNome("Dono Válido");
        dono.setEmail("dono@teste.com");
        dono.setLogin("dono123");
        dono.setSenha("senha123");
        dono.setEndereco("Rua Exemplo, 123, Bairro, Cidade, Estado, CEP"); // Adiciona o endereço
        dono = donoRestauranteRepository.save(dono);
        validDonoId = dono.getId();
    }

    @Test
    void deveRetornar201_QuandoCadastroValido() throws Exception {
        RestauranteRequestDTO request = new RestauranteRequestDTO(
                "Novo Restaurante",
                null,
                "Brasileira",
                validDonoId
        );

        mockMvc.perform(post("/api/restaurante/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Novo Restaurante"))
                .andExpect(jsonPath("$.tipoCozinha").value("Brasileira"));
    }

    @Test
    void deveRetornar409_QuandoNomeDuplicado() throws Exception {
        RestauranteRequestDTO request1 = new RestauranteRequestDTO(
                "Restaurante Duplicado",
                null,
                "Italiana",
                validDonoId
        );
        mockMvc.perform(post("/api/restaurante/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request1)));

        RestauranteRequestDTO request2 = new RestauranteRequestDTO(
                "Restaurante Duplicado",
                null,
                "Mexicana",
                validDonoId
        );

        mockMvc.perform(post("/api/restaurante/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request2)))
                .andExpect(status().isConflict());
    }

    @Test
    void deveRetornar409_QuandoDonoInexistente() throws Exception {
        RestauranteRequestDTO request = new RestauranteRequestDTO(
                "Restaurante Inválido",
                null,
                "Japonesa",
                invalidDonoId
        );

        mockMvc.perform(post("/api/restaurante/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Dono do Restaurante não localizado!"));
    }
}