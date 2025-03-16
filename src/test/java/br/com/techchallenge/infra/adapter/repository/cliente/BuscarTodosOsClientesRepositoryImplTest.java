package br.com.techchallenge.infra.adapter.repository.cliente;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BuscarTodosOsClientesRepositoryImplTest {

    @InjectMocks
    private BuscarTodosOsClientesRepositoryImpl buscarTodosOsClientesRepository;

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTodosOsClientesComSucesso() {
        ClienteRestauranteEntity clienteEntity1 = new ClienteRestauranteEntity(1L, "Nome1", "email1@example.com", "login1", "senha1");
        ClienteRestauranteEntity clienteEntity2 = new ClienteRestauranteEntity(2L, "Nome2", "email2@example.com", "login2", "senha2");
        List<ClienteRestauranteEntity> listaClientesEntity = List.of(clienteEntity1, clienteEntity2);

        when(clienteRestauranteRepository.findAll()).thenReturn(listaClientesEntity);

        List<ClienteRestaurante> resultado = buscarTodosOsClientesRepository.buscarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Nome1", resultado.get(0).getNome());
        assertEquals("Nome2", resultado.get(1).getNome());
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumClienteEncontrado() {
        when(clienteRestauranteRepository.findAll()).thenReturn(Collections.emptyList());

        List<ClienteRestaurante> resultado = buscarTodosOsClientesRepository.buscarTodos();

        assertEquals(0, resultado.size());
    }
}