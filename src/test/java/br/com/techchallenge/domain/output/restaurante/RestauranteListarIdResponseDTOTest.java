package br.com.techchallenge.domain.output.restaurante;

import br.com.techchallenge.domain.entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RestauranteListarIdResponseDTOTest {

    private RestauranteListarIdResponseDTO responseDTO;
    private final String nome = "Restaurante Teste";
    private final Endereco endereco = new Endereco("Rua Teste", "123", "Bairro Teste", "Cidade Teste", "UF");
    private final String tipoCozinha = "Italiana";

    @BeforeEach
    public void configurar() {
        responseDTO = new RestauranteListarIdResponseDTO(
                nome,
                endereco,
                tipoCozinha
        );
    }

    @Test
    @DisplayName("Deve retornar os dados do restaurante corretamente")
    void deveRetornarDadosDoRestauranteCorretamente() {
        assertEquals(nome, responseDTO.nome());
        assertEquals(endereco, responseDTO.endereco());
        assertEquals(tipoCozinha, responseDTO.tipoCozinha());
    }
}