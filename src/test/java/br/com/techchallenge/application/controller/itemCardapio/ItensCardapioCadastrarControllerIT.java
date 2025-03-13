package br.com.techchallenge.application.controller.itemCardapio;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.entity.Restaurante;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class ItensCardapioCadastrarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItensCardapioRepository itensCardapioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Long idRestaurante;

    @BeforeEach
    void setUp() {
        itensCardapioRepository.deleteAll();
        restauranteRepository.deleteAll();

        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setNome("Restaurante Teste");
        restauranteEntity.setTipoCozinha("Italiana");
        restauranteEntity = restauranteRepository.save(restauranteEntity);

        Restaurante restauranteSalvo = new Restaurante();
        restauranteSalvo.setId(restauranteEntity.getId());
        idRestaurante = restauranteSalvo.getId();
    }

    @Test
    void deveRetornar201_QuandoCadastroSucesso() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("nome", "Hamburguer Artesanal");
        data.put("descricao", "Delicioso hamburguer artesanal");
        data.put("preco", 25.90);
        data.put("disponibilidade", "disponível");
        data.put("fotoPrato", "url_da_foto");
        data.put("idRestaurante", idRestaurante);

        String jsonString = objectMapper.writeValueAsString(data);

        mockMvc.perform(post("/api/itens-cardapio/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Hamburguer Artesanal"));
    }

}