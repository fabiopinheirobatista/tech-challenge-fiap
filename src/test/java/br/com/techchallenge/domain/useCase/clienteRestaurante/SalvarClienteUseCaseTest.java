package br.com.techchallenge.domain.useCase.clienteRestaurante;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteJaCadastradoException;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.clienteRestaurante.SalvarClienteInterface;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SalvarClienteUseCaseTest {

    private SalvarClienteInterface clienteRestauranteInterface;
    private ClienteRestauranteRepository clienteRestauranteRepository;
    private SalvarClienteUseCase salvarClienteUseCase;

    @BeforeEach
    void setUp() {
        clienteRestauranteInterface = mock(SalvarClienteInterface.class);
        clienteRestauranteRepository = mock(ClienteRestauranteRepository.class);
        salvarClienteUseCase = new SalvarClienteUseCase(clienteRestauranteInterface, clienteRestauranteRepository);
    }

    @Test
    void deveAtualizarClienteQuandoExistir() throws ClienteNaoEncontradoException {
        ClienteRestauranteEntity clienteRestauranteEntity = new ClienteRestauranteEntity(1L, "Nome Atualizado", "email@atualizado.com", "loginAtualizado", "senhaAtualizada");
        ClienteRestauranteEntity clienteExistente = new ClienteRestauranteEntity(1L, "Nome Antigo", "email@antigo.com", "loginAntigo", "senhaAntiga");
        when(clienteRestauranteRepository.findById(1L)).thenReturn(Optional.of(clienteExistente));
        when(clienteRestauranteInterface.atualizar(clienteExistente)).thenReturn(new ClienteRestaurante(1L, "Nome Atualizado", "email@atualizado.com", "loginAtualizado", "senhaAtualizada"));

        ClienteRestaurante resultado = salvarClienteUseCase.atualizar(clienteRestauranteEntity);

        assertNotNull(resultado);
        assertEquals("Nome Atualizado", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {
        ClienteRestauranteEntity clienteRestauranteEntity = new ClienteRestauranteEntity(1L, "Nome Inexistente", "email@inexistente.com", "loginInexistente", "senhaInexistente");
        when(clienteRestauranteRepository.findById(1L)).thenReturn(Optional.empty());

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () -> {
            salvarClienteUseCase.atualizar(clienteRestauranteEntity);
        });

        assertEquals("Cliente de Restaurante não encontrado", exception.getMessage());
    }

    @Test
    void deveCadastrarClienteQuandoNaoExistir() throws ClienteJaCadastradoException {
        ClienteRestauranteEntity clienteRestauranteEntity = new ClienteRestauranteEntity(1L, "Nome Novo", "email@novo.com", "loginNovo", "senhaNova");
        when(clienteRestauranteRepository.existsByEmail("email@novo.com")).thenReturn(false);
        when(clienteRestauranteInterface.cadastrar(clienteRestauranteEntity)).thenReturn(new ClienteRestaurante(1L, "Nome Novo", "email@novo.com", "loginNovo", "senhaNova"));

        ClienteRestaurante resultado = salvarClienteUseCase.cadastrar(clienteRestauranteEntity);

        assertNotNull(resultado);
        assertEquals("Nome Novo", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoClienteJaCadastrado() {
        ClienteRestauranteEntity clienteRestauranteEntity = new ClienteRestauranteEntity(1L, "Nome Existente", "email@existente.com", "loginExistente", "senhaExistente");
        when(clienteRestauranteRepository.existsByEmail("email@existente.com")).thenReturn(true);

        ClienteJaCadastradoException exception = assertThrows(ClienteJaCadastradoException.class, () -> {
            salvarClienteUseCase.cadastrar(clienteRestauranteEntity);
        });

        assertEquals("Cliente já cadastrado com este email.", exception.getMessage());
    }
}