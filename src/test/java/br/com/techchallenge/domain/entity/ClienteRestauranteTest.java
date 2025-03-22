package br.com.techchallenge.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteRestauranteTest {

    @Test
    @DisplayName("Deve criar cliente do restaurante com sucesso")
    void deveCriarClienteRestauranteComSucesso() {
        Long id = 1L;
        String nome = "Maria Silva";
        String email = "maria.silva@email.com";
        String login = "mariasilva";
        String senha = "senha123";

        ClienteRestaurante cliente = new ClienteRestaurante(id, nome, email, login, senha);

        assertNotNull(cliente);
        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertEquals(login, cliente.getLogin());
        assertEquals(senha, cliente.getSenha());
    }

    @Test
    @DisplayName("Deve atualizar dados do cliente do restaurante")
    void deveAtualizarDadosDoClienteRestaurante() {
        ClienteRestaurante cliente = new ClienteRestaurante();

        Long id = 1L;
        String nomeInicial = "Maria Silva";
        String emailInicial = "maria.silva@email.com";
        String loginInicial = "mariasilva";
        String senhaInicial = "senha123";

        cliente.setId(id);
        cliente.setNome(nomeInicial);
        cliente.setEmail(emailInicial);
        cliente.setLogin(loginInicial);
        cliente.setSenha(senhaInicial);

        assertEquals(id, cliente.getId());
        assertEquals(nomeInicial, cliente.getNome());
        assertEquals(emailInicial, cliente.getEmail());
        assertEquals(loginInicial, cliente.getLogin());
        assertEquals(senhaInicial, cliente.getSenha());

        String novoNome = "Maria Silva Santos";
        String novoEmail = "maria.santos@email.com";
        String novoLogin = "mariasantos";
        String novaSenha = "novaSenha456";

        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
        cliente.setLogin(novoLogin);
        cliente.setSenha(novaSenha);

        assertEquals(id, cliente.getId());
        assertEquals(novoNome, cliente.getNome());
        assertEquals(novoEmail, cliente.getEmail());
        assertEquals(novoLogin, cliente.getLogin());
        assertEquals(novaSenha, cliente.getSenha());
    }
}