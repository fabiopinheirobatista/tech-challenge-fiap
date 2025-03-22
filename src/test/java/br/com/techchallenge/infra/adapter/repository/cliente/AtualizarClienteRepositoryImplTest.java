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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarClienteRepositoryImplTest {

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @InjectMocks
    private AtualizarClienteRepositoryImpl atualizarClienteRepository;

    @Test
    @DisplayName("Deve atualizar cliente com sucesso")
    void deveAtualizarClienteComSucesso() {
        Long id = 1L;
        String nome = "Cliente Atualizado";
        String email = "clienteatualizado@email.com";
        String login = "clienteatualizado";
        String senha = "senha123";

        ClienteRestauranteEntity clienteEntity = new ClienteRestauranteEntity();
        clienteEntity.setId(id);
        clienteEntity.setNome(nome);
        clienteEntity.setEmail(email);
        clienteEntity.setLogin(login);
        clienteEntity.setSenha(senha);

        when(clienteRestauranteRepository.save(clienteEntity)).thenReturn(clienteEntity);

        ClienteRestaurante resultado = atualizarClienteRepository.atualizar(clienteEntity);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
        assertEquals(email, resultado.getEmail());
        assertEquals(login, resultado.getLogin());
        assertEquals(senha, resultado.getSenha());
        verify(clienteRestauranteRepository, times(1)).save(clienteEntity);
    }

    @Test
    @DisplayName("Deve cadastrar novo cliente com sucesso")
    void deveCadastrarNovoClienteComSucesso() {
        Long id = 1L;
        String nome = "Novo Cliente";
        String email = "novocliente@email.com";
        String login = "novocliente";
        String senha = "senha456";

        ClienteRestauranteEntity clienteEntity = new ClienteRestauranteEntity();
        clienteEntity.setId(id);
        clienteEntity.setNome(nome);
        clienteEntity.setEmail(email);
        clienteEntity.setLogin(login);
        clienteEntity.setSenha(senha);

        when(clienteRestauranteRepository.save(clienteEntity)).thenReturn(clienteEntity);

        ClienteRestaurante resultado = atualizarClienteRepository.cadastrar(clienteEntity);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
        assertEquals(email, resultado.getEmail());
        assertEquals(login, resultado.getLogin());
        assertEquals(senha, resultado.getSenha());
        verify(clienteRestauranteRepository, times(1)).save(clienteEntity);
    }
}
