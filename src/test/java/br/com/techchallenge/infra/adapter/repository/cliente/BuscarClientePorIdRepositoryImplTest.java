package br.com.techchallenge.infra.adapter.repository.cliente;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class BuscarClientePorIdRepositoryImplTest {

    @InjectMocks
    private BuscarClientePorIdRepositoryImpl buscarClientePorIdRepository;

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarClienteQuandoEncontrado() throws ClienteNaoEncontradoException {
        Long clienteId = 1L;
        ClienteRestauranteEntity clienteEntity = new ClienteRestauranteEntity(clienteId, "Nome", "email@example.com", "login", "senha");
        ClienteRestaurante cliente = new ClienteRestaurante(clienteId, "Nome", "email@example.com", "login", "senha");

        when(clienteRestauranteRepository.findById(clienteId)).thenReturn(Optional.of(clienteEntity));

        ClienteRestaurante resultado = buscarClientePorIdRepository.buscarPorId(clienteId);

        assertEquals(cliente, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        Long clienteId = 1L;

        when(clienteRestauranteRepository.findById(clienteId)).thenReturn(Optional.empty());

        assertThrows(ClienteNaoEncontradoException.class, () -> buscarClientePorIdRepository.buscarPorId(clienteId));
    }
}