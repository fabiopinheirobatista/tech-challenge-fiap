package br.com.techchallenge.domain.useCase.restaurante;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BuscarRestaurantePorIdUseCaseTest {

    private RestauranteBuscarPorIdInterface repository;
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    @BeforeEach
    void setUp() {
        repository = mock(RestauranteBuscarPorIdInterface.class);
        buscarRestaurantePorIdUseCase = new BuscarRestaurantePorIdUseCase(repository);
    }

    @Test
    void deveRetornarRestauranteQuandoExistir() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante 1", null, "Rua 1", null);
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(restaurante));

        Optional<Restaurante> resultado = buscarRestaurantePorIdUseCase.execute(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Restaurante 1", resultado.get().getNome());
    }

    @Test
    void deveRetornarVazioQuandoNaoExistirRestaurante() {
        when(repository.buscarPorId(1L)).thenReturn(Optional.empty());

        Optional<Restaurante> resultado = buscarRestaurantePorIdUseCase.execute(1L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void deveLancarExcecaoQuandoHouverErroNoRepositório() {
        when(repository.buscarPorId(1L)).thenThrow(new RuntimeException("Erro ao buscar restaurante"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            buscarRestaurantePorIdUseCase.execute(1L);
        });

        assertEquals("Erro ao buscar restaurante", exception.getMessage());
    }
}