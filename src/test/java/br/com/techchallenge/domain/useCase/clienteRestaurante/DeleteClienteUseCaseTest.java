package br.com.techchallenge.domain.useCase.clienteRestaurante;

import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.clienteRestaurante.DeleteClienteInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteClienteUseCaseTest {

    @Mock
    private DeleteClienteInterface clienteRestauranteInterface;

    private DeleteClienteUseCase useCase;

    private final Long clienteId = 1L;

    @BeforeEach
    public void setUp() {
        useCase = new DeleteClienteUseCase(clienteRestauranteInterface);
    }

    @Test
    @DisplayName("Deve deletar o cliente com sucesso")
    void deveDeletarClienteComSucesso() throws ClienteNaoEncontradoException {
        assertDoesNotThrow(() -> useCase.execute(clienteId));

        verify(clienteRestauranteInterface).delete(clienteId);
    }
}