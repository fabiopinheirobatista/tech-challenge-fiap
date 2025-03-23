package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteValidarLoginRequestDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
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
class DonoRestauranteValidarLoginControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DonoRestauranteRepository donoRestauranteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private DonoRestauranteEntity donoExistente;

    @BeforeEach
    void setUp() {
        donoRestauranteRepository.deleteAll();
        donoExistente = donoRestauranteRepository.save(new DonoRestauranteEntity(
                null,
                "Fernanda Lima",
                "Av. Brasil, 500",
                "fernanda@example.com",
                "fernanda.lima",
                "Senha@123",
                null
        ));
    }

    @Test
    void deveValidarLoginComSucesso() throws Exception {
        DonoRestauranteValidarLoginRequestDTO request = new DonoRestauranteValidarLoginRequestDTO(
                donoExistente.getId(), "fernanda.lima", "Senha@123"
        );

        mockMvc.perform(post("/api/donos-restaurante/validar-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário validado com sucesso!"));
    }
    @Test
    void deveRetornar400_QuandoUsuarioNaoEncontrado() throws Exception {
        DonoRestauranteValidarLoginRequestDTO request = new DonoRestauranteValidarLoginRequestDTO(
                999L, // ID inexistente
                "login_invalido",
                "Senha@123"
        );

        mockMvc.perform(post("/api/donos-restaurante/validar-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Usuário inexistente!"));
    }

    // Teste para credenciais inválidas
    @Test
    void deveRetornar401_QuandoCredenciaisInvalidas() throws Exception {
        DonoRestauranteValidarLoginRequestDTO request = new DonoRestauranteValidarLoginRequestDTO(
                donoExistente.getId(),
                "joao.silva",
                "SenhaErrada" // Senha incorreta
        );

        mockMvc.perform(post("/api/donos-restaurante/validar-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Usuário/senha inválidos!"));
    }
}
