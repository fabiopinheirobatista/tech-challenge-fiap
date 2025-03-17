package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarTodosInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class BuscarTodosItensCardapioUseCaseTest {

    @InjectMocks
    private BuscarTodosItensCardapioUseCase buscarTodosItensCardapioUseCase;

    @Mock
    private ItensCardapioBuscarTodosInterface repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTodosItensQuandoExistemItens() {
        ItensCardapio item1 = new ItensCardapio();
        ItensCardapio item2 = new ItensCardapio();
        List<ItensCardapio> itens = List.of(item1, item2);

        when(repository.buscarTodos()).thenReturn(itens);

        List<ItensCardapio> resultado = buscarTodosItensCardapioUseCase.execute();

        assertEquals(2, resultado.size());
        assertEquals(itens, resultado);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistemItens() {
        when(repository.buscarTodos()).thenReturn(Collections.emptyList());

        List<ItensCardapio> resultado = buscarTodosItensCardapioUseCase.execute();

        assertTrue(resultado.isEmpty());
    }
}