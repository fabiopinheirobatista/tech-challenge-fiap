package br.com.techchallenge.domain.input.clienteRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteRestauranteRequestDtoTest {

    private ClienteRestauranteRequestDto requestDto;

    @BeforeEach
    public void setUp() {
        requestDto = new ClienteRestauranteRequestDto("Nome do Cliente", "email@example.com", "login123", "senha123");
    }
}