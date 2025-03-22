package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarTodosInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarTodosRestauranteUseCaseTest {

    @Mock
    private RestauranteBuscarTodosInterface gateway;

    private BuscarTodosRestauranteUseCase useCase;

    private final List<Restaurante> restaurantes = Arrays.asList(
            new Restaurante(1L, "Nome do Restaurante 1", "Endereço do Restaurante 1"),
            new Restaurante(2L, "Nome do Restaurante 2", "Endereço do Restaurante 2")
    );

    @BeforeEach
    public void setUp() {
        useCase = new BuscarTodosRestauranteUseCase(gateway);
    }

    @Test
    @DisplayName("Deve buscar todos os restaurantes com sucesso")
    void deveBuscarTodosOsRestaurantesComSucesso() {
        when(gateway.buscarTodos()).thenReturn(restaurantes);

        List<Restaurante> restaurantesBuscados = useCase.execute();

        assertEquals(restaurantes, restaurantesBuscados);
    }
}