package br.com.techchallenge.domain.output.clienteRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteRestauranteResponseDtoTest {

    private ClienteRestauranteResponseDto responseDto;

    @BeforeEach
    public void setUp() {
        responseDto = new ClienteRestauranteResponseDto(1L, "Nome do Cliente", "Email do Cliente", "Login do Cliente");
    }
}