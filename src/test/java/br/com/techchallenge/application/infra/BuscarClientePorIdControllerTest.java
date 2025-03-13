package br.com.techchallenge.application.infra;

import br.com.techchallenge.application.BuscarClientePorIdController;
import br.com.techchallenge.domain.output.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.domain.useCase.clienteRestaurante.BuscarClientePorIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class BuscarClientePorIdControllerTest {

    @InjectMocks
    private BuscarClientePorIdController buscarClientePorIdController;

    @Mock
    private BuscarClientePorIdUseCase buscarClientePorIdUseCase;


    private Long clienteId = 1L;
    private ClienteRestauranteResponseDto clienteResponse;

    @BeforeEach
    public void setUp() {
        clienteResponse = new ClienteRestauranteResponseDto(1L, "Restaurante Teste", "teste@example.com", "testeLogin");
    }

    @Test
    void deveRetornarClienteComSucesso() {
        when(buscarClientePorIdUseCase.execute(clienteId)).thenReturn(clienteResponse);

        ResponseEntity<?> response = buscarClientePorIdController.buscarPorId(clienteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteResponse, response.getBody());
        verify(buscarClientePorIdUseCase).execute(clienteId);
    }
}