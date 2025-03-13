package br.com.techchallenge.application.controller.itemCardapio;


import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
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

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class ItensCardapioBuscarTodosControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItensCardapioRepository itensCardapioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    void setUp() {
        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setNome("Restaurante Teste");
        restauranteEntity.setTipoCozinha("Italiana");
        restauranteEntity = restauranteRepository.save(restauranteEntity);

        ItensCardapioEntity item = new ItensCardapioEntity(null, "Hamburguer", "Saboroso", 25.90, "disponível", "url_foto", restauranteEntity);
        ItensCardapioEntity itensCardapioEntity = itensCardapioRepository.save(item);
    }

    @Test
    void deveRetornar200_QuandoBuscarTodos() throws Exception {
        mockMvc.perform(get("/api/itens-cardapio/listar-todos"))
                .andExpect(status().isOk());
               //.andExpect(jsonPath("$[0].nome").value("Hamburguer"));
    }

    @Test
    void deveRetornar404_QuandoNenhumItemCadastrado() throws Exception {
        itensCardapioRepository.deleteAll();

        mockMvc.perform(get("/api/itens-cardapio/listar-todos"))
                .andExpect(status().isNotFound());
    }
}
