package br.com.techchallenge.domain.useCase.endereco;

import br.com.techchallenge.domain.entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnderecoTest {

    private Endereco endereco;

    private final String rua = "Rua do Endereço";
    private final String numero = "123";
    private final String cidade = "Cidade do Endereço";

    @BeforeEach
    public void setUp() {
        endereco = new Endereco(rua, numero, cidade);
    }

    @Test
    @DisplayName("Deve retornar os dados do endereço corretamente")
    void deveRetornarDadosDoEnderecoCorretamente() {
        assertEquals(rua, endereco.getRua());
        assertEquals(numero, endereco.getNumero());
        assertEquals(cidade, endereco.getCidade());
    }
}