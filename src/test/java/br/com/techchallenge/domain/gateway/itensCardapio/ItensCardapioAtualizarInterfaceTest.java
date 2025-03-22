package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
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
class ItensCardapioAtualizarInterfaceTest {

    @Mock
    private ItensCardapioAtualizarInterface itensCardapioAtualizarInterface;

    private ItensCardapio itemParaAtualizar;
    private ItensCardapio itemAtualizado;

    @BeforeEach
    public void setUp() {
        itemParaAtualizar = new ItensCardapio();
        itemAtualizado = new ItensCardapio();
    }

    @Test
    @DisplayName("Deve atualizar um item do cardápio com sucesso")
    void deveAtualizarItemDoCardapioComSucesso() {
        when(itensCardapioAtualizarInterface.update(itemParaAtualizar)).thenReturn(itemAtualizado);

        ItensCardapio resultado = itensCardapioAtualizarInterface.update(itemParaAtualizar);

        assertEquals(itemAtualizado, resultado);
        verify(itensCardapioAtualizarInterface).update(itemParaAtualizar);
    }
}