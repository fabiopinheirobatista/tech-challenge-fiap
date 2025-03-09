package br.com.techchallenge.application.controller.restaurante;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class RestauranteBuscarPorIdControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    void deveRetornar200_ComDadosValidos() throws Exception {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNome("Restaurante Teste");
        restaurante.setTipoCozinha("Italiana");
        restauranteRepository.save(restaurante);

        mockMvc.perform(get("/api/restaurante/listar/" + restaurante.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Restaurante Teste"));
    }
}
