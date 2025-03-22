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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class DonoRestauranteListarPorIdControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DonoRestauranteRepository donoRestauranteRepository;

    private DonoRestauranteEntity donoExistente;

    @BeforeEach
    void setUp() {
        donoRestauranteRepository.deleteAll();
        donoExistente = new DonoRestauranteEntity(
                null,
                "Fernanda Lima",
                "Av. Brasil, 500",
                "fernanda@example.com",
                "fernanda.lima",
                "Senha@123",
                null
        );
        donoExistente = donoRestauranteRepository.save(donoExistente);
    }

    @Test
    void deveRetornar200_QuandoDonoEncontrado() throws Exception {
        mockMvc.perform(get("/api/donos-restaurante/listar/{id}", donoExistente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Fernanda Lima"))
                .andExpect(jsonPath("$.email").value("fernanda@example.com"));
    }

    @Test
    void deveRetornar404_QuandoDonoNaoEncontrado() throws Exception {
        mockMvc.perform(get("/api/donos-restaurante/listar/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Dono de Restaurante não encontrado"));
    }
}
