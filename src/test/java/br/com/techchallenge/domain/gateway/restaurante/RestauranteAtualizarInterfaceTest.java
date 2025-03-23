package br.com.techchallenge.domain.gateway.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestauranteAtualizarInterfaceTest {

    @Mock
    private RestauranteAtualizarInterface restauranteAtualizarInterface;

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    @DisplayName("Deve atualizar um restaurante com sucesso")
    void deveAtualizarRestauranteComSucesso() {
        when(restauranteAtualizarInterface.update(restaurante)).thenReturn(restaurante);

        Restaurante resultado = restauranteAtualizarInterface.update(restaurante);

        assertEquals(restaurante, resultado);
        verify(restauranteAtualizarInterface).update(restaurante);
    }
}