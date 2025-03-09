package br.com.techchallenge.application.controller.clienteRestaurante;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DeletarClienteControllerTest {

    @InjectMocks
    private DeletarClienteController deletarClienteController;

    @Mock
    private br.com.techchallenge.domain.useCase.cliente.DeleteClienteUseCase deleteClienteUseCase;

    private final Long clienteId = 1L;

    @Test
    void deveDeletarClienteComSucesso() {
        ResponseEntity<String> response = deletarClienteController.deletar(clienteId);

        verify(deleteClienteUseCase).execute(clienteId);

        // Verifica se o retorno é 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente de Restaurante deletado com sucesso", response.getBody());
    }


}