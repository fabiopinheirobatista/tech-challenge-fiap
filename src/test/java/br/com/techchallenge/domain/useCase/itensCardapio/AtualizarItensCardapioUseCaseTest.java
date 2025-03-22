package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioAtualizarInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AtualizarItensCardapioUseCaseTest {

    @Mock
    private ItensCardapioAtualizarInterface gateway;

    private AtualizarItensCardapioUseCase useCase;

    private final ItensCardapio item = new ItensCardapio(1L, "Nome do Item", 10.0);

    @Test
    @DisplayName("Deve atualizar o item do cardápio com sucesso")
    void deveAtualizarItemDoCardapioComSucesso() {
        ItensCardapio itemAtualizado = new ItensCardapio(1L, "Nome do Item Atualizado", 15.0);

        useCase.execute(itemAtualizado);

        verify(gateway).update(itemAtualizado);
    }
}