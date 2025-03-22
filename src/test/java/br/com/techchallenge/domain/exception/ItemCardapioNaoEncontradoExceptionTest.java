package br.com.techchallenge.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemCardapioNaoEncontradoExceptionTest {

    @Test
    @DisplayName("Deve criar exceção com mensagem correta")
    void deveCriarExcecaoComMensagemCorreta() {
        String mensagem = "Item do cardápio não encontrado com o ID informado";

        ItemCardapioNaoEncontradoException excecao = new ItemCardapioNaoEncontradoException(mensagem);

        assertNotNull(excecao);
        assertEquals(mensagem, excecao.getMessage());
        assertTrue(excecao instanceof RuntimeException);
    }
}