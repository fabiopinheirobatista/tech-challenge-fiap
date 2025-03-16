package br.com.techchallenge.infra.adapter.repository.cliente;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SalvarClienteRepositoryImplTest {

    @InjectMocks
    private SalvarClienteRepositoryImpl salvarClienteRepository;

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        ClienteRestauranteEntity clienteEntity = new ClienteRestauranteEntity(1L, "Nome", "email@example.com", "login", "senha");
        ClienteRestaurante cliente = new ClienteRestaurante(1L, "Nome", "email@example.com", "login", "senha");

        when(clienteRestauranteRepository.save(clienteEntity)).thenReturn(clienteEntity);

        ClienteRestaurante resultado = salvarClienteRepository.atualizar(clienteEntity);

        assertEquals(cliente, resultado);
        verify(clienteRestauranteRepository, times(1)).save(clienteEntity);
    }

    @Test
    void deveCadastrarClienteComSucesso() {
        ClienteRestauranteEntity clienteEntity = new ClienteRestauranteEntity(1L, "Nome", "email@example.com", "login", "senha");
        ClienteRestaurante cliente = new ClienteRestaurante(1L, "Nome", "email@example.com", "login", "senha");

        when(clienteRestauranteRepository.save(clienteEntity)).thenReturn(clienteEntity);

        ClienteRestaurante resultado = salvarClienteRepository.cadastrar(clienteEntity);

        assertEquals(cliente, resultado);
        verify(clienteRestauranteRepository, times(1)).save(clienteEntity);
    }
}