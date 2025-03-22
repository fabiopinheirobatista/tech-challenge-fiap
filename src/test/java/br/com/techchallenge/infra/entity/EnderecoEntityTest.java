package br.com.techchallenge.infra.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EnderecoEntityTest {

    @Test
    @DisplayName("Deve criar uma entidade de endereço com construtor vazio")
    void deveCriarEntidadeEnderecoComConstrutorVazio() {
        EnderecoEntity endereco = new EnderecoEntity();

        assertNotNull(endereco);
        assertNull(endereco.getLogradouro());
        assertNull(endereco.getNumero());
        assertNull(endereco.getComplemento());
        assertNull(endereco.getBairro());
        assertNull(endereco.getCidade());
        assertNull(endereco.getEstado());
        assertNull(endereco.getCep());
    }

    @Test
    @DisplayName("Deve criar uma entidade de endereço com todos os parâmetros")
    void deveCriarEntidadeEnderecoComTodosParametros() {
        String logradouro = "Rua dos Testes";
        String numero = "123";
        String complemento = "Apto 456";
        String bairro = "Bairro Teste";
        String cidade = "Cidade Teste";
        String estado = "SP";
        String cep = "12345-678";

        EnderecoEntity endereco = new EnderecoEntity(logradouro, numero, complemento, bairro, cidade, estado, cep);

        assertNotNull(endereco);
        assertEquals(logradouro, endereco.getLogradouro());
        assertEquals(numero, endereco.getNumero());
        assertEquals(complemento, endereco.getComplemento());
        assertEquals(bairro, endereco.getBairro());
        assertEquals(cidade, endereco.getCidade());
        assertEquals(estado, endereco.getEstado());
        assertEquals(cep, endereco.getCep());
    }

    @Test
    @DisplayName("Deve criar uma entidade de endereço com construtor de três parâmetros")
    void deveCriarEntidadeEnderecoComTresParametros() {
        String rua = "Rua dos Testes";
        String numero = "123";
        String cidade = "Cidade Teste";

        EnderecoEntity endereco = new EnderecoEntity(rua, numero, cidade);

        assertNotNull(endereco);
        assertEquals(rua, endereco.getLogradouro());
        assertEquals(numero, endereco.getNumero());
        assertEquals(cidade, endereco.getCidade());
        assertNull(endereco.getComplemento());
        assertNull(endereco.getBairro());
        assertNull(endereco.getEstado());
        assertNull(endereco.getCep());
    }

    @Test
    @DisplayName("Deve criar uma entidade de endereço a partir de uma string")
    void deveCriarEntidadeEnderecoAPartirDeString() {
        String enderecoCompleto = "Rua dos Testes, 123, Cidade Teste";

        EnderecoEntity endereco = new EnderecoEntity(enderecoCompleto);

        assertNotNull(endereco);
        assertEquals("Rua dos Testes", endereco.getLogradouro());
        assertNull(endereco.getNumero());
        assertNull(endereco.getComplemento());
        assertNull(endereco.getBairro());
        assertNull(endereco.getCidade());
        assertNull(endereco.getEstado());
        assertNull(endereco.getCep());
    }

    @Test
    @DisplayName("Deve alterar os valores dos atributos")
    void deveAlterarValoresAtributos() {
        EnderecoEntity endereco = new EnderecoEntity();

        String logradouro = "Rua dos Testes";
        String numero = "123";
        String complemento = "Apto 456";
        String bairro = "Bairro Teste";
        String cidade = "Cidade Teste";
        String estado = "SP";
        String cep = "12345-678";

        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);

        assertEquals(logradouro, endereco.getLogradouro());
        assertEquals(numero, endereco.getNumero());
        assertEquals(complemento, endereco.getComplemento());
        assertEquals(bairro, endereco.getBairro());
        assertEquals(cidade, endereco.getCidade());
        assertEquals(estado, endereco.getEstado());
        assertEquals(cep, endereco.getCep());
    }

    @Test
    @DisplayName("Deve verificar o método getRua e setRua")
    void deveVerificarMetodosRua() {
        EnderecoEntity endereco = new EnderecoEntity();
        String rua = "Rua dos Testes";

        endereco.setRua(rua);

        assertEquals(rua, endereco.getRua());
        assertEquals(rua, endereco.getLogradouro());
    }

    @Test
    @DisplayName("Deve verificar igualdade entre objetos")
    void deveVerificarIgualdadeEntreObjetos() {
        String logradouro = "Rua dos Testes";
        String numero = "123";
        String complemento = "Apto 456";
        String bairro = "Bairro Teste";
        String cidade = "Cidade Teste";
        String estado = "SP";
        String cep = "12345-678";

        EnderecoEntity endereco1 = new EnderecoEntity(logradouro, numero, complemento, bairro, cidade, estado, cep);
        EnderecoEntity endereco2 = new EnderecoEntity(logradouro, numero, complemento, bairro, cidade, estado, cep);
        EnderecoEntity enderecoDiferente = new EnderecoEntity("Outra Rua", numero, complemento, bairro, cidade, estado, cep);

        assertEquals(endereco1, endereco1);
        assertEquals(endereco1, endereco2);
        assertNotEquals(endereco1, enderecoDiferente);
        assertNotEquals(endereco1, null);
        assertNotEquals(endereco1, new Object());
    }

    @Test
    @DisplayName("Deve verificar o método toString")
    void deveVerificarToString() {
        String logradouro = "Rua dos Testes";
        String numero = "123";
        String complemento = "Apto 456";
        String bairro = "Bairro Teste";
        String cidade = "Cidade Teste";
        String estado = "SP";
        String cep = "12345-678";

        EnderecoEntity endereco = new EnderecoEntity(logradouro, numero, complemento, bairro, cidade, estado, cep);

        String textoToString = endereco.toString();

        assertTrue(textoToString.contains(logradouro));
        assertTrue(textoToString.contains(numero));
        assertTrue(textoToString.contains(complemento));
        assertTrue(textoToString.contains(bairro));
        assertTrue(textoToString.contains(cidade));
        assertTrue(textoToString.contains(estado));
        assertTrue(textoToString.contains(cep));
    }
}