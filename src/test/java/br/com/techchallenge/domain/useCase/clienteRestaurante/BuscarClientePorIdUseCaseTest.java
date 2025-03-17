package br.com.techchallenge.domain.useCase.clienteRestaurante;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.clienteRestaurante.BuscarClientePorIdInterface;
import br.com.techchallenge.domain.output.clienteRestaurante.ClienteRestauranteResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscarClientePorIdUseCaseTest {

    private BuscarClientePorIdInterface buscarClientePorIdInterface;
    private BuscarClientePorIdUseCase buscarClientePorIdUseCase;

    @BeforeEach
    void setUp() {
        buscarClientePorIdInterface = mock(BuscarClientePorIdInterface.class);
        buscarClientePorIdUseCase = new BuscarClientePorIdUseCase(buscarClientePorIdInterface);
    }

    @Test
    void deveRetornarClienteQuandoExistir() throws ClienteNaoEncontradoException {
        Long id = 1L;
        ClienteRestaurante cliente = new ClienteRestaurante(id, "Cliente 1", "cliente1@example.com", "login1", "senha1");
        when(buscarClientePorIdInterface.buscarPorId(id)).thenReturn(cliente);

        ClienteRestauranteResponseDto resultado = buscarClientePorIdUseCase.execute(id);

        assertNotNull(resultado);
        assertEquals("Cliente 1", resultado.nome());
        assertEquals("cliente1@example.com", resultado.email());
    }

    @Test
    void deveLancarExcecaoQuandoHouverErroNoRepositório() throws ClienteNaoEncontradoException {
        Long id = 1L;
        when(buscarClientePorIdInterface.buscarPorId(id)).thenThrow(new RuntimeException("Erro ao buscar cliente"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            buscarClientePorIdUseCase.execute(id);
        });

        assertTrue(exception.getMessage().contains("Erro ao buscar cliente por id"));
    }
}