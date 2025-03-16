package br.com.techchallenge.infra.adapter.repository.cliente;

import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class DeleteClienteRepositoryImplTest {

    @InjectMocks
    private DeleteClienteRepositoryImpl deleteClienteRepository;

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveDeletarClienteComSucesso() throws ClienteNaoEncontradoException {
        Long clienteId = 1L;

        Boolean resultado = deleteClienteRepository.delete(clienteId);

        assertTrue(resultado);
        verify(clienteRestauranteRepository).deleteById(clienteId);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        Long clienteId = 1L;

        doThrow(new RuntimeException()).when(clienteRestauranteRepository).deleteById(clienteId);

        assertThrows(ClienteNaoEncontradoException.class, () -> deleteClienteRepository.delete(clienteId));
    }
}