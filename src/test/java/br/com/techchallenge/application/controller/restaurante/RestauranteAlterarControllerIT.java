package br.com.techchallenge.application.controller.restaurante;


import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.infra.entity.RestauranteEntity;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class RestauranteAlterarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Limpa o banco de dados antes de cada teste
        restauranteRepository.deleteAll();
    }

    @Test
    void deveRetornar200_QuandoAtualizarComSucesso() throws Exception {
        // Cria um restaurante no banco de dados
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNome("Restaurante Teste");
        restaurante.setTipoCozinha("Italiana");
        restaurante = restauranteRepository.save(restaurante);

        // Cria o DTO de atualização
        RestauranteRequestDTO requestDTO = new RestauranteRequestDTO(
                "Restaurante Atualizado", null, "Japonesa", restaurante.getId()
        );

        // Executa a requisição PUT
        mockMvc.perform(put("/api/restaurante/atualizar/" + restaurante.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Restaurante Atualizado"))
                .andExpect(jsonPath("$.tipoCozinha").value("Japonesa"));
    }

    @Test
    void deveRetornar404_QuandoAtualizarRestauranteInexistente() throws Exception {
        RestauranteRequestDTO requestDTO = new RestauranteRequestDTO("Restaurante Atualizado", null, "Japonesa", 999L);

        mockMvc.perform(put("/api/restaurante/atualizar/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isNotFound());
    }

//    @Test
//    void deveRetornar400_QuandoAtualizarComDadosInvalidos() throws Exception {
//        RestauranteRequestDTO requestDTO = new RestauranteRequestDTO(null, null, null, null);
//
//        mockMvc.perform(put("/api/restaurante/atualizar/" + requestDTO.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDTO)))
//                .andExpect(status().isBadRequest());
//    }
}