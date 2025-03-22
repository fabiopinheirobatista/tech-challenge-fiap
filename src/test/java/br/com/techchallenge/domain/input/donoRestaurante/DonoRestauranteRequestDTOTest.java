package br.com.techchallenge.domain.input.donoRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteRequestDTOTest {

    private DonoRestauranteRequestDTO requestDTO;

    @BeforeEach
    public void setUp() {
        requestDTO = new DonoRestauranteRequestDTO(1L, "Nome do Dono", null, "email@example.com", "login123");
    }

    @Test
    @DisplayName("Deve retornar o ID corretamente")
    void deveRetornarIdCorretamente() {
        assertEquals(1L, requestDTO.getId());
    }

    @Test
    @DisplayName("Deve retornar o nome corretamente")
    void deveRetornarNomeCorretamente() {
        assertEquals("Nome do Dono", requestDTO.getNome());
    }

    @Test
    @DisplayName("Deve retornar o email corretamente")
    void deveRetornarEmailCorretamente() {
        assertEquals("email@example.com", requestDTO.getEmail());
    }

    @Test
    @DisplayName("Deve retornar o login corretamente")
    void deveRetornarLoginCorretamente() {
        assertEquals("login123", requestDTO.getLogin());
    }
}