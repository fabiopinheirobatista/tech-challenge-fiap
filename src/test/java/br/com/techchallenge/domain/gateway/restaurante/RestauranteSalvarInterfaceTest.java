package br.com.techchallenge.domain.gateway.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestauranteSalvarInterfaceTest {

    @Mock
    private RestauranteSalvarInterface restauranteSalvarInterface;

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    @DisplayName("Deve salvar um restaurante com sucesso")
    void deveSalvarRestauranteComSucesso() {
        when(restauranteSalvarInterface.salvar(restaurante)).thenReturn(restaurante);

        Restaurante resultado = restauranteSalvarInterface.salvar(restaurante);

        assertEquals(restaurante, resultado);
        verify(restauranteSalvarInterface).salvar(restaurante);
    }
}