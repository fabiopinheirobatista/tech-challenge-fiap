package br.com.techchallenge.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteNaoExisteExceptionTest {

    @Test
    @DisplayName("Deve criar exceção com mensagem correta")
    void deveCriarExcecaoComMensagemCorreta() {
        String mensagem = "Dono de restaurante não existe com o ID informado";

        DonoRestauranteNaoExisteException excecao = new DonoRestauranteNaoExisteException(mensagem);

        assertNotNull(excecao);
        assertEquals(mensagem, excecao.getMessage());
        assertTrue(excecao instanceof Throwable);
    }
}