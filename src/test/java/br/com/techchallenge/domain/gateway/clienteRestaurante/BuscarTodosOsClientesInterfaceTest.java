package br.com.techchallenge.domain.gateway.clienteRestaurante;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarTodosOsClientesInterfaceTest {

    @Mock
    private BuscarTodosOsClientesInterface buscarTodosOsClientesInterface;

    @Test
    @DisplayName("Deve buscar todos os clientes cadastrados")
    void deveBuscarTodosOsClientesCadastrados() {
        ClienteRestaurante cliente1 = new ClienteRestaurante();
        ClienteRestaurante cliente2 = new ClienteRestaurante();
        List<ClienteRestaurante> clientesEsperados = Arrays.asList(cliente1, cliente2);

        when(buscarTodosOsClientesInterface.buscarTodos()).thenReturn(clientesEsperados);

        List<ClienteRestaurante> resultado = buscarTodosOsClientesInterface.buscarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(clientesEsperados, resultado);
        verify(buscarTodosOsClientesInterface).buscarTodos();
    }
}