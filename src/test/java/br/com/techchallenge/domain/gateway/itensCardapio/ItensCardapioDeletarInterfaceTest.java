package br.com.techchallenge.domain.gateway.itensCardapio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItensCardapioDeletarInterfaceTest {

    @Mock
    private ItensCardapioDeletarInterface itensCardapioDeletarInterface;

    private Long itemId;

    @BeforeEach
    public void setUp() {
        itemId = 1L;
    }

    @Test
    @DisplayName("Deve deletar um item do cardápio com sucesso")
    void deveDeletarItemDoCardapioComSucesso() {
        itensCardapioDeletarInterface.deletar(itemId);

        verify(itensCardapioDeletarInterface).deletar(itemId);
    }

    @Test
    @DisplayName("Deve lançar exceção quando tenta deletar um item inexistente")
    void deveLancarExcecaoQuandoTentaDeletarItemInexistente() {
        doThrow(IllegalArgumentException.class).when(itensCardapioDeletarInterface).deletar(itemId);

        assertThrows(IllegalArgumentException.class, () -> itensCardapioDeletarInterface.deletar(itemId));

        verify(itensCardapioDeletarInterface).deletar(itemId);
    }
}