package br.com.techchallenge.application.controller.cliente;

import br.com.techchallenge.application.controller.clienteRestaurante.DeletarClienteController;
import br.com.techchallenge.domain.useCase.clienteRestaurante.DeleteClienteUseCase;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DeletarClienteControllerTest {

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @Mock
    private DeleteClienteUseCase deleteClienteUseCase;

    @InjectMocks
    private DeletarClienteController deletarClienteController;

    @Test
    void deveDeletarClienteComSucesso() {
        Long id = 1L;
        ClienteRestauranteEntity cliente = new ClienteRestauranteEntity();
        when(clienteRestauranteRepository.findById(id)).thenReturn(Optional.of(cliente));

        ResponseEntity<String> response = deletarClienteController.deletar(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente de Restaurante deletado com sucesso", response.getBody());
    }

}