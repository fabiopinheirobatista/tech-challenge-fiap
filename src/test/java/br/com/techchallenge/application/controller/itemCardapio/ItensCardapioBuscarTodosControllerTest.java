package br.com.techchallenge.application.controller.itemCardapio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.com.techchallenge.application.controller.itensCardapio.ItensCardapioBuscarTodosController;
import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.output.itensCardapio.ItensCardapioResponseDTO;
import br.com.techchallenge.domain.useCase.itensCardapio.BuscarTodosItensCardapioUseCase;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ItensCardapioBuscarTodosControllerTest {

    @InjectMocks
    private ItensCardapioBuscarTodosController itensCardapioBuscarTodosController;

    @Mock
    private ItensCardapioRepository itensCardapioRepository;

    @Mock
    private ItensCardapioDTOConverter converter;

    @Mock
    private BuscarTodosItensCardapioUseCase buscarTodosItensCardapioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar404_QuandoNaoHaItensCadastrados() {
        // Act
        ResponseEntity<?> result = itensCardapioBuscarTodosController.buscarTodos();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Nenhum item cadastrado", result.getBody());
    }

}