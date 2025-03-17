package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioDeletarInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ExcluirItensCardapioUseCaseTest {

    @InjectMocks
    private ExcluirItensCardapioUseCase excluirItensCardapioUseCase;

    @Mock
    private ItensCardapioDeletarInterface repositoryDeletar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveExcluirItemQuandoEncontrado() {
        Long id = 1L;
        ItensCardapio item = new ItensCardapio();
        item.setId(id);

        when(repositoryDeletar.buscarPorId(id)).thenReturn(Optional.of(item));
        doNothing().when(repositoryDeletar).deletar(id);

        excluirItensCardapioUseCase.execute(id);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoEncontrado() {
        Long id = 1L;

        when(repositoryDeletar.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(ItemCardapioNaoEncontradoException.class, () -> {
            excluirItensCardapioUseCase.execute(id);
        });
    }
}