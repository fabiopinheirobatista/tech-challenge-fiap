package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioDeletarInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirItensCardapioUseCaseTest {

    @Mock
    private ItensCardapioDeletarInterface gateway;

    private ExcluirItensCardapioUseCase useCase;

    private final Long itemId = 1L;
    private final ItensCardapio item = new ItensCardapio(itemId, "Nome do Item", 10.0);

    @BeforeEach
    public void setUp() {
        useCase = new ExcluirItensCardapioUseCase(gateway);
    }

    @Test
    @DisplayName("Deve excluir o item do cardápio com sucesso")
    void deveExcluirItemDoCardapioComSucesso() {
        when(gateway.buscarPorId(itemId)).thenReturn(Optional.of(item));

        assertDoesNotThrow(() -> useCase.execute(itemId));

        verify(gateway).deletar(itemId);
    }

    @Test
    @DisplayName("Deve lançar exceção quando item do cardápio não for encontrado")
    void deveLancarExcecaoQuandoItemCardapioNaoForEncontrado() {
        when(gateway.buscarPorId(itemId)).thenReturn(Optional.empty());

        assertThrows(ItemCardapioNaoEncontradoException.class, () -> useCase.execute(itemId));
    }
}