package br.com.techchallenge.application.handler;

import br.com.techchallenge.domain.exception.ClienteJaCadastradoException;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    @DisplayName("Deve tratar exceção de cliente não encontrado")
    void deveTratarExcecaoDeClienteNaoEncontrado() {
        String mensagemErro = "Cliente não encontrado com o CPF 123.456.789-10";
        ClienteNaoEncontradoException exception = new ClienteNaoEncontradoException(mensagemErro);

        ResponseEntity<String> resposta = globalExceptionHandler.handleClienteNaoEncontradoException(exception);

        assertNotNull(resposta);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertEquals(mensagemErro, resposta.getBody());
    }

    @Test
    @DisplayName("Deve tratar exceção de cliente já cadastrado")
    void deveTratarExcecaoDeClienteJaCadastrado() {
        String mensagemErro = "Cliente já cadastrado com o CPF 123.456.789-10";
        ClienteJaCadastradoException exception = new ClienteJaCadastradoException(mensagemErro);

        ResponseEntity<String> resposta = globalExceptionHandler.handleClienteJaCadastradoException(exception);

        assertNotNull(resposta);
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals(mensagemErro, resposta.getBody());
    }

    @Test
    @DisplayName("Deve tratar exceção genérica")
    void deveTratarExcecaoGenerica() {
        String mensagemErro = "Erro ao processar a requisição";
        Exception exception = new Exception(mensagemErro);

        ResponseEntity<String> resposta = globalExceptionHandler.handleGeneralException(exception);

        assertNotNull(resposta);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resposta.getStatusCode());
        assertEquals("Ocorreu um erro interno: " + mensagemErro, resposta.getBody());
    }
}