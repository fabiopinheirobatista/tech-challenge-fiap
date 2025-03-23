package br.com.techchallenge.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EnderecoTest {

    @Test
    @DisplayName("Deve criar endereço com dados completos com sucesso")
    void deveCriarEnderecoCompleto() {
        String logradouro = "Avenida Paulista";
        String numero = "1000";
        String complemento = "Sala 123";
        String bairro = "Bela Vista";
        String cidade = "São Paulo";
        String estado = "SP";
        String cep = "01310-100";

        Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, estado, cep);

        assertNotNull(endereco);
        assertEquals(logradouro, endereco.getLogradouro());
        assertEquals(numero, endereco.getNumero());
        assertEquals(complemento, endereco.getComplemento());
        assertEquals(bairro, endereco.getBairro());
        assertEquals(cidade, endereco.getCidade());
        assertEquals(estado, endereco.getEstado());
        assertEquals(cep, endereco.getCep());
        assertEquals(logradouro, endereco.getRua());
    }

    @Test
    @DisplayName("Deve criar endereço com construtor simplificado e verificar getters e setters")
    void deveCriarEnderecoComConstrutorSimplificado() {
        String rua = "Rua Augusta";
        String numero = "500";
        String cidade = "São Paulo";

        Endereco endereco = new Endereco(rua, numero, cidade);

        assertEquals(rua, endereco.getLogradouro());
        assertEquals(numero, endereco.getNumero());
        assertEquals(cidade, endereco.getCidade());

        String novaBairro = "Consolação";
        String novoEstado = "SP";
        String novoCep = "01305-000";
        String novoComplemento = "Apto 42";

        endereco.setBairro(novaBairro);
        endereco.setEstado(novoEstado);
        endereco.setCep(novoCep);
        endereco.setComplemento(novoComplemento);
        endereco.setRua("Rua Augusta Modificada");

        assertEquals(novaBairro, endereco.getBairro());
        assertEquals(novoEstado, endereco.getEstado());
        assertEquals(novoCep, endereco.getCep());
        assertEquals(novoComplemento, endereco.getComplemento());
        assertEquals("Rua Augusta Modificada", endereco.getRua());
        assertEquals("Rua Augusta Modificada", endereco.getLogradouro());
    }
}