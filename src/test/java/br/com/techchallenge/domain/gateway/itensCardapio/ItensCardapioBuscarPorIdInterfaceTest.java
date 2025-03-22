package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItensCardapioBuscarPorIdInterfaceTest {

    @Mock
    private ItensCardapioBuscarPorIdInterface itensCardapioBuscarPorIdInterface;

    private Long itemId;
    private ItensCardapio item;

    @BeforeEach
    public void setUp() {
        itemId = 1L;
        item = new ItensCardapio();
    }

    @Test
    @DisplayName("Deve buscar um item do cardápio pelo ID com sucesso")
    void deveBuscarItemDoCardapioPorIdComSucesso() {
        when(itensCardapioBuscarPorIdInterface.buscarPorId(itemId)).thenReturn(Optional.ofNullable(item));

        Optional<ItensCardapio> resultado = itensCardapioBuscarPorIdInterface.buscarPorId(itemId);

        assertEquals(item, resultado);
        verify(itensCardapioBuscarPorIdInterface).buscarPorId(itemId);
    }
}