package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class DonoRestauranteExcluirControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DonoRestauranteRepository donoRestauranteRepository;

    private DonoRestauranteEntity donoExistente;

    @BeforeEach
    void setUp() {
        donoRestauranteRepository.deleteAll();
        donoExistente = donoRestauranteRepository.save(new DonoRestauranteEntity(
                null,
                "Carlos Oliveira",
                "Av. Paulista, 1000",
                "carlos@example.com",
                "carlos.oliveira",
                "Senha@123",
                null
        ));
    }

    @Test
    void deveExcluirDonoRestaurante() throws Exception {
        mockMvc.perform(delete("/api/donos-restaurante/excluir/" + donoExistente.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Dono de Restaurante deletado com sucesso"));
    }

    @Test
    void deveRetornar404_QuandoDonoNaoEncontrado() throws Exception {
        mockMvc.perform(delete("/api/donos-restaurante/excluir/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Não existe Dono de Restaurante com o ID informado"));
    }
}