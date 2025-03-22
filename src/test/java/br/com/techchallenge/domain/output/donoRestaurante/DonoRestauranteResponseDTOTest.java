package br.com.techchallenge.domain.output.donoRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteResponseDTOTest {

    private DonoRestauranteResponseDTO responseDTO;

    @BeforeEach
    public void setUp() {
        responseDTO = new DonoRestauranteResponseDTO(1L, "Nome do Dono", "Endereço do Dono", "Email do Dono", "Login do Dono");
    }
}