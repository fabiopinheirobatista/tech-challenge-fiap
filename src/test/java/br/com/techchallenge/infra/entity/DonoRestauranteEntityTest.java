package br.com.techchallenge.infra.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteEntityTest {

    @Test
    @DisplayName("Deve criar uma entidade de dono de restaurante com construtor vazio")
    void deveCriarEntidadeDonoComConstrutorVazio() {
        DonoRestauranteEntity dono = new DonoRestauranteEntity();

        assertNotNull(dono);
        assertNull(dono.getId());
        assertNull(dono.getNome());
        assertNull(dono.getEndereco());
        assertNull(dono.getEmail());
        assertNull(dono.getLogin());
        assertNull(dono.getSenha());
        assertNull(dono.getDataUltimaAlteracao());
    }

    @Test
    @DisplayName("Deve criar uma entidade de dono de restaurante com todos os parâmetros")
    void deveCriarEntidadeDonoComTodosParametros() {
        Long id = 1L;
        String nome = "Dono Teste";
        String endereco = "Rua dos Testes, 123";
        String email = "dono@teste.com";
        String login = "donoteste";
        String senha = "senha123";
        LocalDate dataUltimaAlteracao = LocalDate.now();

        DonoRestauranteEntity dono = new DonoRestauranteEntity(id, nome, endereco, email, login, senha, dataUltimaAlteracao);

        assertNotNull(dono);
        assertEquals(id, dono.getId());
        assertEquals(nome, dono.getNome());
        assertEquals(endereco, dono.getEndereco());
        assertEquals(email, dono.getEmail());
        assertEquals(login, dono.getLogin());
        assertEquals(senha, dono.getSenha());
        assertEquals(dataUltimaAlteracao, dono.getDataUltimaAlteracao());
    }

    @Test
    @DisplayName("Deve alterar os valores dos atributos")
    void deveAlterarValoresAtributos() {
        DonoRestauranteEntity dono = new DonoRestauranteEntity();

        Long id = 1L;
        String nome = "Dono Teste";
        String endereco = "Rua dos Testes, 123";
        String email = "dono@teste.com";
        String login = "donoteste";
        String senha = "senha123";
        LocalDate dataUltimaAlteracao = LocalDate.now();

        dono.setId(id);
        dono.setNome(nome);
        dono.setEndereco(endereco);
        dono.setEmail(email);
        dono.setLogin(login);
        dono.setSenha(senha);
        dono.setDataUltimaAlteracao(dataUltimaAlteracao);

        assertEquals(id, dono.getId());
        assertEquals(nome, dono.getNome());
        assertEquals(endereco, dono.getEndereco());
        assertEquals(email, dono.getEmail());
        assertEquals(login, dono.getLogin());
        assertEquals(senha, dono.getSenha());
        assertEquals(dataUltimaAlteracao, dono.getDataUltimaAlteracao());
    }

    @Test
    @DisplayName("Deve verificar que o toString não inclui a senha")
    void deveVerificarToStringNaoIncluiSenha() {
        Long id = 1L;
        String nome = "Dono Teste";
        String endereco = "Rua dos Testes, 123";
        String email = "dono@teste.com";
        String login = "donoteste";
        String senha = "senha123";
        LocalDate dataUltimaAlteracao = LocalDate.now();

        DonoRestauranteEntity dono = new DonoRestauranteEntity(id, nome, endereco, email, login, senha, dataUltimaAlteracao);

        String textoToString = dono.toString();

        assertTrue(textoToString.contains(id.toString()));
        assertTrue(textoToString.contains(nome));
        assertTrue(textoToString.contains(endereco));
        assertTrue(textoToString.contains(email));
        assertTrue(textoToString.contains(login));
        assertFalse(textoToString.contains(senha));
        assertTrue(textoToString.contains(dataUltimaAlteracao.toString()));
    }

    @Test
    @DisplayName("Deve verificar igualdade entre objetos")
    void deveVerificarIgualdadeEntreObjetos() {
        Long id = 1L;
        String nome = "Dono Teste";
        String endereco = "Rua dos Testes, 123";
        String email = "dono@teste.com";
        String login = "donoteste";
        String senha = "senha123";
        LocalDate dataUltimaAlteracao = LocalDate.now();

        DonoRestauranteEntity dono1 = new DonoRestauranteEntity(id, nome, endereco, email, login, senha, dataUltimaAlteracao);
        DonoRestauranteEntity dono2 = new DonoRestauranteEntity(id, nome, endereco, email, login, senha, dataUltimaAlteracao);
        DonoRestauranteEntity donoDiferente = new DonoRestauranteEntity(2L, nome, endereco, email, login, senha, dataUltimaAlteracao);

        assertEquals(dono1, dono1);
        assertNotEquals(dono1, donoDiferente);
        assertNotEquals(dono1, null);
        assertNotEquals(dono1, new Object());
    }
}