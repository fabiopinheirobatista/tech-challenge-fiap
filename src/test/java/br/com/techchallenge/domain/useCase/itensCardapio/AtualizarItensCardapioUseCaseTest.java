package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioAtualizarInterface;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarPorIdInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AtualizarItensCardapioUseCaseTest {

    @InjectMocks
    private AtualizarItensCardapioUseCase atualizarItensCardapioUseCase;

    @Mock
    private ItensCardapioAtualizarInterface atualizarRepository;

    @Mock
    private ItensCardapioBuscarPorIdInterface buscarPorIdRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarItemQuandoEncontrado() {
        ItensCardapio item = new ItensCardapio();
        item.setId(1L);

        when(buscarPorIdRepository.buscarPorId(1L)).thenReturn(Optional.of(item));
        when(atualizarRepository.update(item)).thenReturn(item);

        ItensCardapio resultado = atualizarItensCardapioUseCase.execute(item);

        assertEquals(item, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoEncontrado() {
        ItensCardapio item = new ItensCardapio();
        item.setId(1L);

        when(buscarPorIdRepository.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(ItemCardapioNaoEncontradoException.class, () -> {
            atualizarItensCardapioUseCase.execute(item);
        });
    }
}