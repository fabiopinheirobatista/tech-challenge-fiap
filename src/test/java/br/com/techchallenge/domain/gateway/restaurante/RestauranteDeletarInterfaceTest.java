package br.com.techchallenge.domain.gateway.restaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestauranteDeletarInterfaceTest {

    @Mock
    private RestauranteDeletarInterface restauranteDeletarInterface;

    private Long restauranteId;

    @BeforeEach
    public void setUp() {
        restauranteId = 1L;
    }

    @Test
    @DisplayName("Deve deletar um restaurante com sucesso")
    void deveDeletarRestauranteComSucesso() {
        assertDoesNotThrow(() -> restauranteDeletarInterface.deletar(restauranteId));
        verify(restauranteDeletarInterface).deletar(restauranteId);
    }
}