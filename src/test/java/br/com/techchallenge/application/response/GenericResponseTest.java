package br.com.techchallenge.application.response;

import br.com.techchallenge.domain.generic.OutputInterface;
import br.com.techchallenge.domain.generic.OutputStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenericResponseTest {

    @InjectMocks
    private GenericResponse genericResponse;

    @Mock
    private OutputInterface outputInterface;

    @Mock
    private OutputStatus outputStatus;

    @Test
    @DisplayName("Deve retornar status 200 OK quando o código for 200")
    void deveRetornarStatus200QuandoCodigoFor200() {
        String resposta = "Operação realizada com sucesso";

        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputStatus.getCode()).thenReturn(200);
        when(outputInterface.getBody()).thenReturn(resposta);

        ResponseEntity<Object> resultado = genericResponse.response(outputInterface);

        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(resposta, resultado.getBody());
    }

    @Test
    @DisplayName("Deve retornar status 201 CREATED quando o código for 201")
    void deveRetornarStatus201QuandoCodigoFor201() {
        String resposta = "Recurso criado com sucesso";

        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputStatus.getCode()).thenReturn(201);
        when(outputInterface.getBody()).thenReturn(resposta);

        ResponseEntity<Object> resultado = genericResponse.response(outputInterface);

        assertNotNull(resultado);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(resposta, resultado.getBody());
    }

    @Test
    @DisplayName("Deve retornar status 404 NOT_FOUND quando o código for 404")
    void deveRetornarStatus404QuandoCodigoFor404() {
        String resposta = "Recurso não encontrado";

        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputStatus.getCode()).thenReturn(404);
        when(outputInterface.getBody()).thenReturn(resposta);

        ResponseEntity<Object> resultado = genericResponse.response(outputInterface);

        assertNotNull(resultado);
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
        assertEquals(resposta, resultado.getBody());
    }

    @Test
    @DisplayName("Deve retornar status 204 NO_CONTENT quando o código for 204")
    void deveRetornarStatus204QuandoCodigoFor204() {
        String resposta = null;

        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputStatus.getCode()).thenReturn(204);
        when(outputInterface.getBody()).thenReturn(resposta);

        ResponseEntity<Object> resultado = genericResponse.response(outputInterface);

        assertNotNull(resultado);
        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
        assertEquals(resposta, resultado.getBody());
    }

    @Test
    @DisplayName("Deve retornar status 422 UNPROCESSABLE_ENTITY quando o código for 422")
    void deveRetornarStatus422QuandoCodigoFor422() {
        String resposta = "Requisição inválida";

        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputStatus.getCode()).thenReturn(422);
        when(outputInterface.getBody()).thenReturn(resposta);

        ResponseEntity<Object> resultado = genericResponse.response(outputInterface);

        assertNotNull(resultado);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resultado.getStatusCode());
        assertEquals(resposta, resultado.getBody());
    }

    @Test
    @DisplayName("Deve retornar status 500 INTERNAL_SERVER_ERROR para código não tratado")
    void deveRetornarStatus500ParaCodigoNaoTratado() {
        String resposta = "Erro desconhecido";

        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputStatus.getCode()).thenReturn(999);
        when(outputInterface.getBody()).thenReturn(resposta);

        ResponseEntity<Object> resultado = genericResponse.response(outputInterface);

        assertNotNull(resultado);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resultado.getStatusCode());
        assertEquals(resposta, resultado.getBody());
    }
}