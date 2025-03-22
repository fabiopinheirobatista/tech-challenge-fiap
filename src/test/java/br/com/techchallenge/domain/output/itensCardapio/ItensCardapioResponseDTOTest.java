package br.com.techchallenge.domain.output.itensCardapio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ItensCardapioResponseDTOTest {

    private ItensCardapioResponseDTO responseDTO;
    private final Long id = 1L;
    private final String nome = "Prato Teste";
    private final String descricao = "Descrição do Prato";
    private final double preco = 25.90;
    private final String disponibilidade = "Disponível";
    private final String fotoPrato = "url/da/foto.jpg";
    private final Long idRestaurante = 2L;

    @BeforeEach
    public void configurar() {
        responseDTO = new ItensCardapioResponseDTO(
                id,
                nome,
                descricao,
                preco,
                disponibilidade,
                fotoPrato,
                idRestaurante
        );
    }

    @Test
    @DisplayName("Deve retornar os dados do item de cardápio corretamente")
    void deveRetornarDadosDoItemCardapioCorretamente() {
        assertEquals(id, responseDTO.id());
        assertEquals(nome, responseDTO.nome());
        assertEquals(descricao, responseDTO.descricao());
        assertEquals(preco, responseDTO.preco());
        assertEquals(disponibilidade, responseDTO.disponibilidade());
        assertEquals(fotoPrato, responseDTO.fotoPrato());
        assertEquals(idRestaurante, responseDTO.idRestaurante());
    }
}