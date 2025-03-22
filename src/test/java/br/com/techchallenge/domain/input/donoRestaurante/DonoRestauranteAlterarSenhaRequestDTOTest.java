package br.com.techchallenge.domain.input.donoRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteAlterarSenhaRequestDTOTest {

    private DonoRestauranteAlterarSenhaRequestDTO requestDTO;

    @BeforeEach
    public void setUp() {
        requestDTO = new DonoRestauranteAlterarSenhaRequestDTO(1L, "email@example.com", "senhaAtual123", "novaSenha123");
    }

    @Test
    @DisplayName("Deve retornar o ID corretamente")
    void deveRetornarIdCorretamente() {
        assertEquals(1L, requestDTO.getId());
    }

    @Test
    @DisplayName("Deve retornar o email corretamente")
    void deveRetornarEmailCorretamente() {
        assertEquals("email@example.com", requestDTO.getEmail());
    }

    @Test
    @DisplayName("Deve retornar a senha atual corretamente")
    void deveRetornarSenhaAtualCorretamente() {
        assertEquals("senhaAtual123", requestDTO.getSenhaAtual());
    }

    @Test
    @DisplayName("Deve retornar a nova senha corretamente")
    void deveRetornarNovaSenhaCorretamente() {
        assertEquals("novaSenha123", requestDTO.getNovaSenha());
    }
}