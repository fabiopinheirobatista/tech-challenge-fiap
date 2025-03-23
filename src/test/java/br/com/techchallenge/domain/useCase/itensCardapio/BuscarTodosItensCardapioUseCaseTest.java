package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarTodosInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarTodosItensCardapioUseCaseTest {

    @Mock
    private ItensCardapioBuscarTodosInterface gateway;

    private BuscarTodosItensCardapioUseCase useCase;

    private final List<ItensCardapio> itens = Arrays.asList(
            new ItensCardapio(1L, "Nome do Item 1", 10.0),
            new ItensCardapio(2L, "Nome do Item 2", 15.0)
    );

    @BeforeEach
    public void setUp() {
        useCase = new BuscarTodosItensCardapioUseCase(gateway);
    }

    @Test
    @DisplayName("Deve buscar todos os itens do cardápio com sucesso")
    void deveBuscarTodosItensDoCardapioComSucesso() {
        when(gateway.buscarTodos()).thenReturn(itens);

        List<ItensCardapio> itensBuscados = useCase.execute();

        assertEquals(itens, itensBuscados);
    }
}