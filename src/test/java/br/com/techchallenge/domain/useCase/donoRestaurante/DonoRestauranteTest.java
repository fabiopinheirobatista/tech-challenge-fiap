package br.com.techchallenge.domain.useCase.donoRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DonoRestauranteTest {

    private DonoRestaurante donoRestaurante;

    private final Long id = 1L;
    private final String nome = "Nome do Dono";
    private final String email = "Email do Dono";

    @BeforeEach
    public void setUp() {
        donoRestaurante = new DonoRestaurante(id, nome, email);
    }

    @Test
    @DisplayName("Deve retornar os dados do dono do restaurante corretamente")
    void deveRetornarDadosDoDonoDoRestauranteCorretamente() {
        assertEquals(id, donoRestaurante.getId());
        assertEquals(nome, donoRestaurante.getNome());
        assertEquals(email, donoRestaurante.getEmail());
    }
}