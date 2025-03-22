package br.com.techchallenge.infra.adapter.repository.cliente;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarTodosOsClientesRepositoryImplTest {

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @InjectMocks
    private BuscarTodosOsClientesRepositoryImpl buscarTodosOsClientesRepository;

    @Test
    @DisplayName("Deve retornar lista de clientes quando existirem clientes cadastrados")
    void deveRetornarListaDeClientesQuandoExistiremClientesCadastrados() {
        List<ClienteRestauranteEntity> listaEntities = new ArrayList<>();

        ClienteRestauranteEntity cliente1 = new ClienteRestauranteEntity();
        cliente1.setId(1L);
        cliente1.setNome("Cliente Um");
        cliente1.setEmail("cliente1@email.com");
        cliente1.setLogin("cliente1");
        cliente1.setSenha("senha123");

        ClienteRestauranteEntity cliente2 = new ClienteRestauranteEntity();
        cliente2.setId(2L);
        cliente2.setNome("Cliente Dois");
        cliente2.setEmail("cliente2@email.com");
        cliente2.setLogin("cliente2");
        cliente2.setSenha("senha456");

        listaEntities.add(cliente1);
        listaEntities.add(cliente2);

        when(clienteRestauranteRepository.findAll()).thenReturn(listaEntities);

        List<ClienteRestaurante> resultado = buscarTodosOsClientesRepository.buscarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertEquals("Cliente Um", resultado.get(0).getNome());
        assertEquals("cliente1@email.com", resultado.get(0).getEmail());
        assertEquals("cliente1", resultado.get(0).getLogin());
        assertEquals("senha123", resultado.get(0).getSenha());

        assertEquals(2L, resultado.get(1).getId());
        assertEquals("Cliente Dois", resultado.get(1).getNome());
        assertEquals("cliente2@email.com", resultado.get(1).getEmail());
        assertEquals("cliente2", resultado.get(1).getLogin());
        assertEquals("senha456", resultado.get(1).getSenha());

        verify(clienteRestauranteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem clientes cadastrados")
    void deveRetornarListaVaziaQuandoNaoExistiremClientesCadastrados() {
        List<ClienteRestauranteEntity> listaVazia = new ArrayList<>();

        when(clienteRestauranteRepository.findAll()).thenReturn(listaVazia);

        List<ClienteRestaurante> resultado = buscarTodosOsClientesRepository.buscarTodos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(clienteRestauranteRepository, times(1)).findAll();
    }
}
