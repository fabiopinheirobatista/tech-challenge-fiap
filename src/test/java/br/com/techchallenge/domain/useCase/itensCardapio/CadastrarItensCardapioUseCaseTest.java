package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioSalvarInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CadastrarItensCardapioUseCaseTest {

    @Mock
    private ItensCardapioSalvarInterface gateway;

    private CadastrarItensCardapioUseCase useCase;

    private final ItensCardapio item = new ItensCardapio(null, "Nome do Item", 10.0);

    @BeforeEach
    public void setUp() {
        useCase = new CadastrarItensCardapioUseCase(gateway);
    }

    @Test
    @DisplayName("Deve cadastrar o item do cardápio com sucesso")
    void deveCadastrarItemDoCardapioComSucesso() throws RestauranteNaoEncontradoException {
        assertDoesNotThrow(() -> useCase.execute(item));

        verify(gateway).salvar(item);
    }
}