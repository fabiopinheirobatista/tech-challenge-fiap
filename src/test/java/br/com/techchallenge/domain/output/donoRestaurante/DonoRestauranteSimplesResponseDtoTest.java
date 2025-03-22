package br.com.techchallenge.domain.output.donoRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteSimplesResponseDtoTest {

    private DonoRestauranteSimplesResponseDto responseDto;

    @BeforeEach
    public void setUp() {
        responseDto = new DonoRestauranteSimplesResponseDto("Nome do Dono", "Endereço do Dono", "email@exemplo.com");
    }

    @Test
    @DisplayName("Deve retornar os dados do DonoRestaurante corretamente")
    void deveRetornarDadosDoDonoRestauranteCorretamente() {
        assertEquals("Nome do Dono", responseDto.nome());
        assertEquals("Endereço do Dono", responseDto.endereco());
        assertEquals("email@exemplo.com", responseDto.email());
    }
}