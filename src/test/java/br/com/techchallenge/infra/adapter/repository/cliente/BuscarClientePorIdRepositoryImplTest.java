package br.com.techchallenge.infra.adapter.repository.cliente;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarClientePorIdRepositoryImplTest {

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @InjectMocks
    private BuscarClientePorIdRepositoryImpl buscarClientePorIdRepository;

    @Test
    @DisplayName("Deve retornar cliente quando encontrar por ID")
    void deveRetornarClienteQuandoEncontrarPorId() throws ClienteNaoEncontradoException {
        Long id = 1L;
        String nome = "Cliente Teste";
        String email = "cliente@email.com";
        String login = "clienteteste";
        String senha = "senha123";

        ClienteRestauranteEntity clienteEntity = new ClienteRestauranteEntity();
        clienteEntity.setId(id);
        clienteEntity.setNome(nome);
        clienteEntity.setEmail(email);
        clienteEntity.setLogin(login);
        clienteEntity.setSenha(senha);

        when(clienteRestauranteRepository.findById(id)).thenReturn(Optional.of(clienteEntity));

        ClienteRestaurante resultado = buscarClientePorIdRepository.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
        assertEquals(email, resultado.getEmail());
        assertEquals(login, resultado.getLogin());
        assertEquals(senha, resultado.getSenha());
        verify(clienteRestauranteRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar exceção quando cliente não for encontrado por ID")
    void deveLancarExcecaoQuandoClienteNaoForEncontradoPorId() {
        Long id = 99L;

        when(clienteRestauranteRepository.findById(id)).thenReturn(Optional.empty());

        ClienteNaoEncontradoException excecao = assertThrows(ClienteNaoEncontradoException.class, () -> {
            buscarClientePorIdRepository.buscarPorId(id);
        });

        assertEquals("Cliente de Restaurante não encontrado", excecao.getMessage());
        verify(clienteRestauranteRepository, times(1)).findById(id);
    }
}