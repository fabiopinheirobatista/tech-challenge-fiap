package br.com.techchallenge.domain.useCase.restaurante;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarTodosInterface;
import br.com.techchallenge.domain.useCase.restaurante.BuscarTodosRestauranteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class BuscarTodosRestauranteUseCaseTest {

    private RestauranteBuscarTodosInterface repository;
    private BuscarTodosRestauranteUseCase buscarTodosRestauranteUseCase;

    @BeforeEach
    void setUp() {
        repository = mock(RestauranteBuscarTodosInterface.class);
        buscarTodosRestauranteUseCase = new BuscarTodosRestauranteUseCase(repository);
    }

    @Test
    void deveRetornarTodosOsRestaurantesQuandoExistirem() {

        Restaurante restaurante1 = new Restaurante(1L, "Restaurante 1", null, "Rua 1", null);
        Restaurante restaurante2 = new Restaurante(2L, "Restaurante 2", null, "Rua 2", null);
        List<Restaurante> restaurantes = Arrays.asList(restaurante1, restaurante2);

        when(repository.buscarTodos()).thenReturn(restaurantes);

        List<Restaurante> resultado = buscarTodosRestauranteUseCase.execute();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Restaurante 1", resultado.get(0).getNome());
        assertEquals("Restaurante 2", resultado.get(1).getNome());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremRestaurantes() {
        when(repository.buscarTodos()).thenReturn(List.of());

        List<Restaurante> resultado = buscarTodosRestauranteUseCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveLancarExcecaoQuandoHouverErroNoRepositório() {
        when(repository.buscarTodos()).thenThrow(new RuntimeException("Erro ao buscar restaurantes"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            buscarTodosRestauranteUseCase.execute();
        });

        assertEquals("Erro ao buscar restaurantes", exception.getMessage());
    }
}
