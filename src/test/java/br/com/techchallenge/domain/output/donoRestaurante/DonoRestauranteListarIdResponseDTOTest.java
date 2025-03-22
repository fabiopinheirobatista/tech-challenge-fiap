package br.com.techchallenge.domain.output.donoRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteListarIdResponseDTOTest {

    private DonoRestauranteListarIdResponseDTO responseDTO;

    @BeforeEach
    public void setUp() {
        responseDTO = new DonoRestauranteListarIdResponseDTO(1L, "Nome do Dono", "Endereço do Dono", "Email do Dono", "Login do Dono");
    }
}