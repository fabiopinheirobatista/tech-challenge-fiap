package br.com.techchallenge.domain.input.itensCardapio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ItensCardapioRequestDTOTest {

    private ItensCardapioRequestDTO requestDTO;

    @BeforeEach
    public void setUp() {
        requestDTO = new ItensCardapioRequestDTO("Nome do Item", "Descrição do Item", 10.0, "Disponível", "URL da Foto", 1L);
    }

    @Test
    @DisplayName("Deve retornar o nome corretamente")
    void deveRetornarNomeCorretamente() {
        assertEquals("Nome do Item", requestDTO.getNome());
    }

    @Test
    @DisplayName("Deve retornar a descrição corretamente")
    void deveRetornarDescricaoCorretamente() {
        assertEquals("Descrição do Item", requestDTO.getDescricao());
    }

    @Test
    @DisplayName("Deve retornar o preço corretamente")
    void deveRetornarPrecoCorretamente() {
        assertEquals(10.0, requestDTO.getPreco());
    }

    @Test
    @DisplayName("Deve retornar a disponibilidade corretamente")
    void deveRetornarDisponibilidadeCorretamente() {
        assertEquals("Disponível", requestDTO.getDisponibilidade());
    }

    @Test
    @DisplayName("Deve retornar a URL da foto corretamente")
    void deveRetornarUrlFotoCorretamente() {
        assertEquals("URL da Foto", requestDTO.getFotoPrato());
    }

    @Test
    @DisplayName("Deve retornar o ID do restaurante corretamente")
    void deveRetornarIdRestauranteCorretamente() {
        assertEquals(1L, requestDTO.getIdRestaurante());
    }
}