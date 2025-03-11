package br.com.techchallenge.application.controller.itemCardapio;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.input.itensCardapio.ItensCardapioRequestDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = TechChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ItensCardapioAlterarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItensCardapioRepository itensCardapioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Long idRestaurante;
    private Long idItem;

    @BeforeEach
    void setUp() {
        itensCardapioRepository.deleteAll();
        restauranteRepository.deleteAll();

        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setNome("Restaurante Teste");
        restauranteEntity.setTipoCozinha("Italiana");
        restauranteEntity = restauranteRepository.save(restauranteEntity);

        idRestaurante = restauranteEntity.getId();

        ItensCardapioEntity item = new ItensCardapioEntity();
        item.setRestaurante(restauranteEntity);

        ItensCardapioEntity itensCardapioEntity = itensCardapioRepository.save(item);
        idItem = itensCardapioEntity.getId();
    }

    @Test
    void deveRetornar200_QuandoAtualizarSucesso() throws Exception {
        ItensCardapioRequestDTO requestDTO = new ItensCardapioRequestDTO("Pizza Atualizada", "Nova Descrição", 29.90, "disponível", "url_foto_atualizada", idRestaurante);

        mockMvc.perform(put("/api/itens-cardapio/atualizar/" + idItem)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pizza Atualizada"));
    }

    @Test
    void deveRetornar404_QuandoAtualizarItemInexistente() throws Exception {
        ItensCardapioRequestDTO requestDTO = new ItensCardapioRequestDTO("Pizza Atualizada", "Nova Descrição", 29.90, "disponível", "url_foto_atualizada", idRestaurante);

        mockMvc.perform(put("/api/itens-cardapio/atualizar/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isNotFound());
    }

}
