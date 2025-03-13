package br.com.techchallenge.application.infra;

import br.com.techchallenge.application.AtualizarClienteController;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.input.clienteRestaurante.ClienteRestauranteRequestDto;
import br.com.techchallenge.domain.useCase.clienteRestaurante.SalvarClienteUseCase;
import br.com.techchallenge.infra.converter.clienteRestaurante.ClienteRestauranteDtoConverter;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarClienteControllerTest {

    @InjectMocks
    private AtualizarClienteController atualizarClienteController;

    @Mock
    private SalvarClienteUseCase salvarClienteUseCase;

    @Mock
    private ClienteRestauranteDtoConverter converter;

    private Long clienteId = 1L;

    private ClienteRestauranteRequestDto clienteRequest;

    @BeforeEach
    public void setUp() {
        clienteRequest = new ClienteRestauranteRequestDto("Restaurante Teste", "teste@example.com", "testeLogin", "senhaSegura");
    }

    @Test
    void deveRetornarResponseComSucesso() throws ClienteNaoEncontradoException {
        when(converter.dtoParaEntity(clienteId, clienteRequest)).thenReturn(new ClienteRestauranteEntity());

        ResponseEntity<String> response = atualizarClienteController.atualizar(clienteId, clienteRequest);

        assertEquals(ResponseEntity.ok("Cliente de Restaurante atualizado com sucesso"), response);
        verify(salvarClienteUseCase).atualizar(any(ClienteRestauranteEntity.class));
    }

    @Test
    void deveRetornarClienteNaoEncontradoException() throws ClienteNaoEncontradoException {

        when(converter.dtoParaEntity(anyLong(), any(ClienteRestauranteRequestDto.class)))
                .thenReturn(new ClienteRestauranteEntity());
        doThrow(new ClienteNaoEncontradoException("Cliente de Restaurante não encontrado"))
                .when(salvarClienteUseCase).atualizar(any(ClienteRestauranteEntity.class));

        ClienteNaoEncontradoException thrown = assertThrows(
                ClienteNaoEncontradoException.class,
                () -> atualizarClienteController.atualizar(clienteId, clienteRequest),
                "Expected atualizarClienteController.atualizar to throw, but it didn't"
        );

        assertEquals("Cliente de Restaurante não encontrado", thrown.getMessage());
    }
}