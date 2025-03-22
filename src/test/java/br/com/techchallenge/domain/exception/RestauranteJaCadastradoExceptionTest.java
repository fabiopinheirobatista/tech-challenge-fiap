package br.com.techchallenge.domain.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestauranteJaCadastradoExceptionTest {

    @Test
    @DisplayName("Deve criar exceção com mensagem correta")
    void deveCriarExcecaoComMensagemCorreta() {
        String mensagem = "Restaurante já cadastrado com o nome informado";

        RestauranteJaCadastradoException excecao = new RestauranteJaCadastradoException(mensagem);

        assertNotNull(excecao);
        assertEquals(mensagem, excecao.getMessage());
        assertTrue(excecao instanceof Throwable);
    }
}