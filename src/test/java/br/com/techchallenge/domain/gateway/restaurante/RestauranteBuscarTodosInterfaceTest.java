package br.com.techchallenge.domain.gateway.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestauranteBuscarTodosInterfaceTest {

    @Mock
    private RestauranteBuscarTodosInterface restauranteBuscarTodosInterface;

    private List<Restaurante> restaurantes;

    @BeforeEach
    public void setUp() {
        restaurantes = List.of(new Restaurante(), new Restaurante(), new Restaurante());
    }

    @Test
    @DisplayName("Deve buscar todos os restaurantes com sucesso")
    void deveBuscarTodosOsRestaurantesComSucesso() {
        when(restauranteBuscarTodosInterface.buscarTodos()).thenReturn(restaurantes);

        List<Restaurante> resultado = restauranteBuscarTodosInterface.buscarTodos();

        assertEquals(restaurantes, resultado);
        verify(restauranteBuscarTodosInterface).buscarTodos();
    }
}