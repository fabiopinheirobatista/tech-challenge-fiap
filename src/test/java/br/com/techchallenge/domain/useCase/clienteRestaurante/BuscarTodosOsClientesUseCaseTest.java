package br.com.techchallenge.domain.useCase.clienteRestaurante;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.gateway.clienteRestaurante.BuscarTodosOsClientesInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BuscarTodosOsClientesUseCaseTest {

    @Mock
    private BuscarTodosOsClientesInterface gateway;

    private BuscarTodosOsClientesUseCase useCase;

    private final List<ClienteRestaurante> clientes = Arrays.asList(
            new ClienteRestaurante(1L, "Nome do Cliente 1", "Email do Cliente 1"),
            new ClienteRestaurante(2L, "Nome do Cliente 2", "Email do Cliente 2")
    );

    @BeforeEach
    public void setUp() {
        useCase = new BuscarTodosOsClientesUseCase(gateway);
    }
}