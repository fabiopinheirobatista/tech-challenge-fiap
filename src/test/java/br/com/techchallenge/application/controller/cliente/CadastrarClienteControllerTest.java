package br.com.techchallenge.application.controller.cliente;

import br.com.techchallenge.application.controller.clienteRestaurante.CadastrarClienteController;
import br.com.techchallenge.domain.exception.ClienteJaCadastradoException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CadastrarClienteControllerTest {

    @InjectMocks
    private CadastrarClienteController cadastrarClienteController;

    @Mock
    private SalvarClienteUseCase salvarClienteUseCase;

    @Mock
    private ClienteRestauranteDtoConverter converter;

    private ClienteRestauranteRequestDto requestDto;
    private ClienteRestauranteEntity clienteEntity;

    @BeforeEach
    void setUp() {
        requestDto = new ClienteRestauranteRequestDto("Joao Teste", "Nome Teste", "teste@example.com", "loginTeste");
        clienteEntity = new ClienteRestauranteEntity(1L, "Nome Teste", "teste@example.com", "loginTeste", "senhaTeste");
    }

    @Test
    void deveCadastrarClienteComSucesso() throws ClienteJaCadastradoException {
        when(converter.dtoParaEntity(requestDto)).thenReturn(clienteEntity);

        ResponseEntity<String> response = cadastrarClienteController.cadastrar(requestDto);

        verify(converter).dtoParaEntity(requestDto);
        verify(salvarClienteUseCase).cadastrar(clienteEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente de Restaurante cadastrado com sucesso", response.getBody());
    }

}