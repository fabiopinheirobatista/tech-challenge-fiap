package br.com.techchallenge.application.controller.itemCardapio;


import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.entity.ItensCardapio;
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
class ItensCardapioBuscarPorIdControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItensCardapioRepository itensCardapioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private Long idItem;

    @BeforeEach
    void setUp() {
        itensCardapioRepository.deleteAll();

        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setNome("Restaurante Teste");
        restauranteEntity.setTipoCozinha("Italiana");
        restauranteEntity = restauranteRepository.save(restauranteEntity);

        ItensCardapioEntity item = new ItensCardapioEntity(null, "Hamburguer", "Saboroso", 25.90, "disponível", "url_foto", restauranteEntity);
        ItensCardapioEntity itensCardapioEntity = itensCardapioRepository.save(item);
        idItem = itensCardapioEntity.getId();
    }

    @Test
    void deveRetornar200_QuandoBuscarPorId() throws Exception {
        mockMvc.perform(get("/api/itens-cardapio/listar/" + idItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Hamburguer"));
    }

    @Test
    void deveRetornar404_QuandoBuscarPorIdInexistente() throws Exception {
        mockMvc.perform(get("/api/itens-cardapio/listar/999"))
                .andExpect(status().isNotFound());
    }
}
