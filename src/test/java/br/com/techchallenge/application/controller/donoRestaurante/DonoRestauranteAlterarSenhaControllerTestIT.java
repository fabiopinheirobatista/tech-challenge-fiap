package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteAlterarSenhaRequestDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class DonoRestauranteAlterarSenhaControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DonoRestauranteRepository donoRestauranteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private DonoRestauranteEntity donoExistente;

    @BeforeEach
    void setUp() {
        donoRestauranteRepository.deleteAll();
        donoExistente = new DonoRestauranteEntity(
                null,
                "Maria Silva",
                "Rua Principal, 123",
                "maria@example.com",
                "maria.silva",
                "SenhaAntiga@123",
                null
        );
        donoExistente = donoRestauranteRepository.save(donoExistente);
    }

    @Test
    void deveRetornar200_QuandoSenhaAlteradaComSucesso() throws Exception {
        DonoRestauranteAlterarSenhaRequestDTO request = new DonoRestauranteAlterarSenhaRequestDTO(
                donoExistente.getId(),
                "maria@example.com",
                "SenhaAntiga@123",
                "NovaSenha@456"
        );

        mockMvc.perform(put("/api/donos-restaurante/alterar-senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Atualização realizada com sucesso!"));

        DonoRestauranteEntity donoAtualizado = donoRestauranteRepository.findById(donoExistente.getId()).orElseThrow();
        assertEquals("NovaSenha@456", donoAtualizado.getSenha());
    }

    @Test
    void deveRetornar404_QuandoCredenciaisInvalidas() throws Exception {
        DonoRestauranteAlterarSenhaRequestDTO request = new DonoRestauranteAlterarSenhaRequestDTO(
                donoExistente.getId(),
                "email_errado@example.com",
                "SenhaErrada",
                "NovaSenha@456"
        );

        mockMvc.perform(put("/api/donos-restaurante/alterar-senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Atualização não realizada pois o ID informado não foi localizado ou o email/senha estão incorretos!"));
    }
}