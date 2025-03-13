package br.com.techchallenge.application.controller.restaurante;


import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class RestauranteDeletarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    void deveRetornar200_QuandoExclusaoSucesso() throws Exception {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNome("Restaurante Teste");
        restaurante.setTipoCozinha("Italiana");
        restauranteRepository.save(restaurante);

        mockMvc.perform(delete("/api/restaurante/" + restaurante.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Restaurante excluído com sucesso"));
    }

    @Test
    void deveRetornar404_QuandoDeletarRestauranteInexistente() throws Exception {
        mockMvc.perform(delete("/api/restaurante/999"))
                .andExpect(status().isNotFound());
    }
}
