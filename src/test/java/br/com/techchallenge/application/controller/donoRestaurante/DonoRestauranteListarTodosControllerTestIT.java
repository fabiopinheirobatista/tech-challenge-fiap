package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.input.donoRestaurante.*;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class DonoRestauranteListarTodosControllerTestIT {

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
                "Maria Silva",
                "Rua Principal, 123",
                "maria@example.com",
                "maria.silva",
                "SenhaAntiga@123",
                null
        ));
    }

    @Test
    void deveListarTodosDonosRestaurante() throws Exception {
        mockMvc.perform(get("/api/donos-restaurante/listar-todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria Silva"));
    }
}