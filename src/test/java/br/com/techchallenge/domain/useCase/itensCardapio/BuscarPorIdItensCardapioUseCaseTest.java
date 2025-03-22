package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarPorIdInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarPorIdItensCardapioUseCaseTest {

    @Mock
    private ItensCardapioBuscarPorIdInterface gateway;

    private BuscarPorIdItensCardapioUseCase useCase;

    private final Long itemId = 1L;
    private final ItensCardapio item = new ItensCardapio(itemId, "Nome do Item", 10.0);

    @BeforeEach
    public void setUp() {
        useCase = new BuscarPorIdItensCardapioUseCase(gateway);
    }

    @Test
    @DisplayName("Deve buscar o item do cardápio pelo ID com sucesso")
    void deveBuscarItemDoCardapioPeloIdComSucesso() {
        when(gateway.buscarPorId(itemId)).thenReturn(Optional.of(item));

        Optional<ItensCardapio> itemBuscado = useCase.execute(itemId);

        assertEquals(Optional.of(item), itemBuscado);
    }
}