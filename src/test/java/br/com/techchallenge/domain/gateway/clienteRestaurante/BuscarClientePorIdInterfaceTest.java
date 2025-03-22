package br.com.techchallenge.domain.gateway.clienteRestaurante;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarClientePorIdInterfaceTest {

    @Mock
    private BuscarClientePorIdInterface buscarClientePorIdInterface;

    @Test
    @DisplayName("Deve buscar cliente por id quando cliente existir")
    void deveBuscarClientePorIdQuandoClienteExistir() throws ClienteNaoEncontradoException {
        Long clienteId = 1L;
        ClienteRestaurante clienteEsperado = new ClienteRestaurante();

        when(buscarClientePorIdInterface.buscarPorId(clienteId)).thenReturn(clienteEsperado);

        ClienteRestaurante resultado = buscarClientePorIdInterface.buscarPorId(clienteId);

        assertNotNull(resultado);
        assertEquals(clienteEsperado, resultado);
        verify(buscarClientePorIdInterface).buscarPorId(clienteId);
    }
}