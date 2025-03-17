package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarPorIdInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class BuscarPorIdItensCardapioUseCaseTest {

    @InjectMocks
    private BuscarPorIdItensCardapioUseCase buscarPorIdItensCardapioUseCase;

    @Mock
    private ItensCardapioBuscarPorIdInterface repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarItemQuandoEncontrado() {
        Long id = 1L;
        ItensCardapio item = new ItensCardapio();
        item.setId(id);

        when(repository.buscarPorId(id)).thenReturn(Optional.of(item));

        Optional<ItensCardapio> resultado = buscarPorIdItensCardapioUseCase.execute(id);

        assertTrue(resultado.isPresent());
        assertEquals(item, resultado.get());
    }

    @Test
    void deveRetornarVazioQuandoItemNaoEncontrado() {
        Long id = 1L;

        when(repository.buscarPorId(id)).thenReturn(Optional.empty());

        Optional<ItensCardapio> resultado = buscarPorIdItensCardapioUseCase.execute(id);

        assertTrue(resultado.isEmpty());
    }
}