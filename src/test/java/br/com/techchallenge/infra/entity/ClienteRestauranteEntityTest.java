package br.com.techchallenge.infra.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteRestauranteEntityTest {

    @Test
    @DisplayName("Deve criar uma entidade de cliente com construtor vazio")
    void deveCriarEntidadeClienteComConstrutorVazio() {
        ClienteRestauranteEntity cliente = new ClienteRestauranteEntity();

        assertNotNull(cliente);
        assertNull(cliente.getId());
        assertNull(cliente.getNome());
        assertNull(cliente.getEmail());
        assertNull(cliente.getLogin());
        assertNull(cliente.getSenha());
    }

    @Test
    @DisplayName("Deve criar uma entidade de cliente com todos os parâmetros")
    void deveCriarEntidadeClienteComTodosParametros() {
        Long id = 1L;
        String nome = "Cliente Teste";
        String email = "cliente@teste.com";
        String login = "clienteteste";
        String senha = "senha123";

        ClienteRestauranteEntity cliente = new ClienteRestauranteEntity(id, nome, email, login, senha);

        assertNotNull(cliente);
        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertEquals(login, cliente.getLogin());
        assertEquals(senha, cliente.getSenha());
    }

    @Test
    @DisplayName("Deve alterar os valores dos atributos")
    void deveAlterarValoresAtributos() {
        ClienteRestauranteEntity cliente = new ClienteRestauranteEntity();

        Long id = 1L;
        String nome = "Cliente Teste";
        String email = "cliente@teste.com";
        String login = "clienteteste";
        String senha = "senha123";

        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setLogin(login);
        cliente.setSenha(senha);

        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertEquals(login, cliente.getLogin());
        assertEquals(senha, cliente.getSenha());
    }

    @Test
    @DisplayName("Deve verificar que o toString não inclui a senha")
    void deveVerificarToStringNaoIncluiSenha() {
        Long id = 1L;
        String nome = "Cliente Teste";
        String email = "cliente@teste.com";
        String login = "clienteteste";
        String senha = "senha123";

        ClienteRestauranteEntity cliente = new ClienteRestauranteEntity(id, nome, email, login, senha);

        String textoToString = cliente.toString();

        assertTrue(textoToString.contains(id.toString()));
        assertTrue(textoToString.contains(nome));
        assertTrue(textoToString.contains(email));
        assertTrue(textoToString.contains(login));
        assertFalse(textoToString.contains(senha));
    }
}