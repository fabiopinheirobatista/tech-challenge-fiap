package br.com.techchallenge.domain.useCase.clienteRestaurante;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.gateway.clienteRestaurante.BuscarTodosOsClientesInterface;
import br.com.techchallenge.domain.output.clienteRestaurante.ClienteRestauranteResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BuscarTodosOsClientesUseCaseTest {

    private BuscarTodosOsClientesInterface buscarClientesInterface;
    private BuscarTodosOsClientesUseCase buscarTodosOsClientesUseCase;

    @BeforeEach
    void setUp() {
        buscarClientesInterface = mock(BuscarTodosOsClientesInterface.class);
        buscarTodosOsClientesUseCase = new BuscarTodosOsClientesUseCase(buscarClientesInterface);
    }

    @Test
    void deveRetornarListaDeClientes() {
        ClienteRestaurante cliente1 = new ClienteRestaurante(1L, "Cliente 1", "cliente1@example.com", "login1", "senha1");
        ClienteRestaurante cliente2 = new ClienteRestaurante(2L, "Cliente 2", "cliente2@example.com", "login2", "senha2");
        List<ClienteRestaurante> clientes = Arrays.asList(cliente1, cliente2);

        when(buscarClientesInterface.buscarTodos()).thenReturn(clientes);

        List<ClienteRestauranteResponseDto> resultado = buscarTodosOsClientesUseCase.execute();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Cliente 1", resultado.get(0).nome());
        assertEquals("Cliente 2", resultado.get(1).nome());
    }

    @Test
    void deveLancarExcecaoQuandoHouverErroNoRepositório() {
        when(buscarClientesInterface.buscarTodos()).thenThrow(new RuntimeException("Erro ao buscar clientes"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            buscarTodosOsClientesUseCase.execute();
        });

        assertTrue(exception.getMessage().contains("Erro ao buscar todos os clientes"));
    }
}