package br.com.techchallenge.domain.useCase.clienteRestaurante;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.clienteRestaurante.DeleteClienteInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteClienteUseCaseTest {

    @InjectMocks
    private DeleteClienteUseCase deleteClienteUseCase;

    @Mock
    private DeleteClienteInterface deleteClienteInterface;

    @Test
    void deveDeletarClienteQuandoExistir() throws ClienteNaoEncontradoException {
        Long id = 1L;

        when(deleteClienteInterface.delete(id)).thenReturn(true);

        deleteClienteUseCase.execute(id);

        verify(deleteClienteInterface, times(1)).delete(id);
    }

    @Test
    void deveLancarExcecaoQuandoHouverErroNoRepositório() throws ClienteNaoEncontradoException {
        Long id = 1L;

        doThrow(new RuntimeException("Erro original")).when(deleteClienteInterface).delete(id);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            deleteClienteUseCase.execute(id);
        });

        assertTrue(exception.getMessage().contains("Erro ao deletar cliente"));
    }
}