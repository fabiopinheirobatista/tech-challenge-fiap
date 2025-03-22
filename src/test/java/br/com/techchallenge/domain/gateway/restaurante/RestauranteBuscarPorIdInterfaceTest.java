package br.com.techchallenge.domain.gateway.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestauranteBuscarPorIdInterfaceTest {

    @Mock
    private RestauranteBuscarPorIdInterface restauranteBuscarPorIdInterface;

    private Long restauranteId;
    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restauranteId = 1L;
        restaurante = new Restaurante();
    }

    @Test
    @DisplayName("Deve buscar um restaurante por ID com sucesso")
    void deveBuscarRestaurantePorIdComSucesso() {
        when(restauranteBuscarPorIdInterface.buscarPorId(restauranteId)).thenReturn(Optional.of(restaurante));

        Optional<Restaurante> resultado = restauranteBuscarPorIdInterface.buscarPorId(restauranteId);

        assertTrue(resultado.isPresent());
        assertEquals(restaurante, resultado.get());
        verify(restauranteBuscarPorIdInterface).buscarPorId(restauranteId);
    }
}