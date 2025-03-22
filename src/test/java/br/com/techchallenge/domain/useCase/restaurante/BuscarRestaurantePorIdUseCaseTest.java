package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import br.com.techchallenge.domain.useCase.restaurante.BuscarRestaurantePorIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarRestaurantePorIdUseCaseTest {

    @Mock
    private RestauranteBuscarPorIdInterface gateway;

    private BuscarRestaurantePorIdUseCase useCase;

    private final Long restauranteId = 1L;
    private final Restaurante restaurante = new Restaurante(restauranteId, "Nome do Restaurante", "Endereço do Restaurante");

    @BeforeEach
    public void setUp() {
        useCase = new BuscarRestaurantePorIdUseCase(gateway);
    }

    @Test
    @DisplayName("Deve buscar o restaurante pelo ID com sucesso")
    void deveBuscarRestaurantePeloIdComSucesso() {
        when(gateway.buscarPorId(restauranteId)).thenReturn(Optional.of(restaurante));

        Optional<Restaurante> restauranteBuscado = useCase.execute(restauranteId);

        assertEquals(Optional.of(restaurante), restauranteBuscado);
    }
}