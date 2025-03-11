package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@ActiveProfiles("test")
class RestauranteBuscarTodosControllerIT {

    private MockMvc mockMvc;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteDTOConverter restauranteDTOConverter;

    private RestauranteBuscarTodosController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RestauranteBuscarTodosController(restauranteRepository, restauranteDTOConverter);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void buscarTodos_DeveRetornarListaDeRestaurantes_QuandoExistirem() throws Exception {
        RestauranteEntity entity1 = new RestauranteEntity();
        entity1.setId(1L);
        entity1.setNome("Restaurante A");
        entity1.setTipoCozinha("Italiana");

        RestauranteEntity entity2 = new RestauranteEntity();
        entity2.setId(2L);
        entity2.setNome("Restaurante B");
        entity2.setTipoCozinha("Japonesa");

        List<RestauranteEntity> entities = List.of(entity1, entity2);
        Restaurante restaurante1 = new Restaurante(1L, "Restaurante A", null, "Italiana", null);
        Restaurante restaurante2 = new Restaurante(2L, "Restaurante B", null, "Japonesa", null);

        Mockito.when(restauranteRepository.findAll()).thenReturn(entities);
        Mockito.when(restauranteDTOConverter.restauranteEntityToRestaurante(entity1)).thenReturn(restaurante1);
        Mockito.when(restauranteDTOConverter.restauranteEntityToRestaurante(entity2)).thenReturn(restaurante2);
        Mockito.when(restauranteDTOConverter.restauranteParaResponseDto(restaurante1))
                .thenReturn(new RestauranteListarTodosResponseDTO(1L, "Restaurante A", null, "Italiana"));
        Mockito.when(restauranteDTOConverter.restauranteParaResponseDto(restaurante2))
                .thenReturn(new RestauranteListarTodosResponseDTO(2L, "Restaurante B", null, "Japonesa"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/restaurante/listar-todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Restaurante A"))
                .andExpect(jsonPath("$[1].tipoCozinha").value("Japonesa"));
    }

    @Test
    void buscarTodos_DeveRetornar404_QuandoListaVazia() throws Exception {
        Mockito.when(restauranteRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/restaurante/listar-todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Não existem Restaurantes cadastrados."));
    }

    @Test
    void buscarTodos_DeveRetornar500_QuandoOcorrerErro() throws Exception {
        Mockito.when(restauranteRepository.findAll()).thenThrow(new RuntimeException("Erro simulado"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/restaurante/listar-todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}