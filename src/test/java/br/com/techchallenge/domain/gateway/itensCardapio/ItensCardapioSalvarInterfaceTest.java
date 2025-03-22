package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
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
class ItensCardapioSalvarInterfaceTest {

    @Mock
    private ItensCardapioSalvarInterface itensCardapioSalvarInterface;

    private ItensCardapio item;

    @BeforeEach
    public void setUp() {
        item = new ItensCardapio();
    }

    @Test
    @DisplayName("Deve salvar um item do cardápio com sucesso")
    void deveSalvarItemDoCardapioComSucesso() {
        when(itensCardapioSalvarInterface.salvar(item)).thenReturn(item);

        ItensCardapio resultado = itensCardapioSalvarInterface.salvar(item);

        assertEquals(item, resultado);
        verify(itensCardapioSalvarInterface).salvar(item);
    }
}