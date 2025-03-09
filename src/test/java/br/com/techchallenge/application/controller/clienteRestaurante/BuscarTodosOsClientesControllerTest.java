package br.com.techchallenge.application.controller.clienteRestaurante;

import br.com.techchallenge.domain.output.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.domain.useCase.clienteRestaurante.BuscarTodosOsClientesUseCase;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BuscarTodosOsClientesControllerTest {

    @InjectMocks
    private BuscarTodosOsClientesController buscarTodosOsClientesController;

    @Mock
    private BuscarTodosOsClientesUseCase buscarTodosOsClientesUseCase;

    private List<ClienteRestauranteResponseDto> clientes;

    @BeforeEach
    public void setUp() {
        clientes = Collections.singletonList(new ClienteRestauranteResponseDto(1L, "Cliente Teste", "cliente@example.com", "testeLogin"));
    }

    @Test
    void deveRetornarClientesComSucesso() {
        // Arrange
        when(buscarTodosOsClientesUseCase.execute()).thenReturn(clientes);

        // Act
        ResponseEntity<?> response = buscarTodosOsClientesController.buscarTodos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

}