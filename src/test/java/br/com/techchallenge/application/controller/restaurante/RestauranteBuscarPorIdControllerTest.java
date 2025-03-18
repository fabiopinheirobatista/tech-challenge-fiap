package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RestauranteBuscarPorIdControllerTest {

    @Mock
    private BuscarRestaurantePorIdUseCase useCase;

    @Mock
    private RestauranteDTOConverter converter;

    @InjectMocks
    private RestauranteBuscarPorIdController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar200_QuandoRestauranteEncontrado() {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante Teste");
        restaurante.setTipoCozinha("Italiana");
        Endereco endereco = new Endereco();

        RestauranteListarTodosResponseDTO responseDTO = new RestauranteListarTodosResponseDTO(1L, "Restaurante Teste", endereco, "Italiana");

        when(useCase.execute(anyLong())).thenReturn(Optional.of(restaurante));
        when(converter.restauranteParaResponseDto(restaurante)).thenReturn(responseDTO);

        ResponseEntity<?> response = controller.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void deveRetornar404_QuandoRestauranteNaoEncontrado() {
        when(useCase.execute(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.buscarPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Restaurante com o ID informado não foi encontrado", response.getBody());
    }
}