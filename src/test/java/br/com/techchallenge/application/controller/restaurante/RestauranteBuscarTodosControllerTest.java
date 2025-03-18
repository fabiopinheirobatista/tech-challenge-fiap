package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RestauranteBuscarTodosControllerTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteDTOConverter converter;

    @InjectMocks
    private RestauranteBuscarTodosController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodos_DeveRetornarListaDeRestaurantes_QuandoExistirem() {
        RestauranteEntity restauranteEntity1 = new RestauranteEntity(1L, "Restaurante A", new Endereco(), "Italiana", null);
        RestauranteEntity restauranteEntity2 = new RestauranteEntity(2L, "Restaurante B", new Endereco(), "Japonesa", null);

        List<RestauranteEntity> restaurantes = List.of(restauranteEntity1, restauranteEntity2);

        // Mock do repository
        when(restauranteRepository.findAll()).thenReturn(restaurantes);

        // Mock do conversor - Certifique-se de que está passando RestauranteEntity!
        when(converter.restauranteParaResponseDto(any(Restaurante.class)))
                .thenAnswer(invocation -> {
                    Restaurante restaurante = invocation.getArgument(0);
                    return new RestauranteListarTodosResponseDTO(restaurante.getId(), restaurante.getNome(), restaurante.getEndereco(), restaurante.getTipoCozinha());
                });

        // Executa a requisição no controller
        ResponseEntity<?> response = controller.buscarTodos();

        // Verifica a resposta
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<RestauranteListarTodosResponseDTO> responseBody = (List<RestauranteListarTodosResponseDTO>) response.getBody();
        assertNotNull(responseBody);
        assertEquals(2, responseBody.size());
        assertEquals("Restaurante A", responseBody.get(0).nome());
        assertEquals("Japonesa", responseBody.get(1).tipoCozinha());
    }

    @Test
    void buscarTodos_DeveRetornar404_QuandoListaVazia() {
        when(restauranteRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = controller.buscarTodos();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Não existem Restaurantes cadastrados.", response.getBody());
    }

    @Test
    void buscarTodos_DeveRetornar500_QuandoOcorrerErro() {
        when(restauranteRepository.findAll()).thenThrow(new RuntimeException("Erro simulado"));

        ResponseEntity<?> response = controller.buscarTodos();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}