package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.useCase.restaurante.ExcluirRestauranteUseCase;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class RestauranteDeletarControllerTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteDTOConverter converter;

    @Mock
    private ExcluirRestauranteUseCase useCase;

    @InjectMocks
    private RestauranteDeletarController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar200_QuandoExclusaoSucesso() throws RestauranteNaoEncontradoException {
        Long restauranteId = 1L;

        ResponseEntity<?> response = controller.deletar(restauranteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Restaurante excluído com sucesso", response.getBody());
        verify(useCase).execute(restauranteId);
    }

}