package br.com.techchallenge.domain.input.donoRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteValidarLoginRequestDTOTest {

    private DonoRestauranteValidarLoginRequestDTO requestDTO;

    @BeforeEach
    public void setUp() {
        requestDTO = new DonoRestauranteValidarLoginRequestDTO(1L, "login123", "senha123");
    }

    @Test
    @DisplayName("Deve retornar o ID corretamente")
    void deveRetornarIdCorretamente() {
        assertEquals(1L, requestDTO.getId());
    }

    @Test
    @DisplayName("Deve retornar o login corretamente")
    void deveRetornarLoginCorretamente() {
        assertEquals("login123", requestDTO.getLogin());
    }

    @Test
    @DisplayName("Deve retornar a senha corretamente")
    void deveRetornarSenhaCorretamente() {
        assertEquals("senha123", requestDTO.getSenha());
    }
}