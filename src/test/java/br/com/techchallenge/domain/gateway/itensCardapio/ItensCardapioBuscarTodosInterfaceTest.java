package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
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
class ItensCardapioBuscarTodosInterfaceTest {

    @Mock
    private ItensCardapioBuscarTodosInterface itensCardapioBuscarTodosInterface;

    private List<ItensCardapio> itensCardapio;

    @BeforeEach
    public void setUp() {
        itensCardapio = List.of(new ItensCardapio(), new ItensCardapio(), new ItensCardapio());
    }

    @Test
    @DisplayName("Deve buscar todos os itens do cardápio com sucesso")
    void deveBuscarTodosItensDoCardapioComSucesso() {
        when(itensCardapioBuscarTodosInterface.buscarTodos()).thenReturn(itensCardapio);

        List<ItensCardapio> resultado = itensCardapioBuscarTodosInterface.buscarTodos();

        assertEquals(itensCardapio, resultado);
        verify(itensCardapioBuscarTodosInterface).buscarTodos();
    }
}