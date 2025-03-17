package br.com.techchallenge.application.controller.itemCardapio;

import br.com.techchallenge.application.controller.itensCardapio.ItensCardapioCadastrarController;
import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioSalvarInterface;
import br.com.techchallenge.domain.input.itensCardapio.ItensCardapioRequestDTO;
import br.com.techchallenge.domain.output.itensCardapio.ItensCardapioResponseDTO;
import br.com.techchallenge.domain.useCase.itensCardapio.CadastrarItensCardapioUseCase;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItensCardapioCadastrarControllerTest {

    @InjectMocks
    private ItensCardapioCadastrarController controller;

    @Mock
    private ItensCardapioSalvarInterface repository;

    @Mock
    private ItensCardapioRepository itensCardapioRepository;


    @Mock
    private ItensCardapioDTOConverter converter;

    @Mock
    private CadastrarItensCardapioUseCase cadastrarUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar201_QuandoCadastroSucesso() throws RestauranteNaoEncontradoException {
        ItensCardapioRequestDTO requestDTO = new ItensCardapioRequestDTO("Pizza", "Descrição", 29.90, "disponível", "url_foto", 1L);
        ItensCardapioResponseDTO responseDTO = new ItensCardapioResponseDTO(1L, "Pizza", "Descrição", 29.90, "disponível", "url_foto", 1L);
        ItensCardapio itemMock = new ItensCardapio();
        ItensCardapio itemSalvoMock = new ItensCardapio();

        when(converter.dtoToDomain(requestDTO)).thenReturn(itemMock);
        when(cadastrarUseCase.execute(itemMock)).thenReturn(itemSalvoMock);
        when(converter.domainToDto(itemSalvoMock)).thenReturn(responseDTO);

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(new RestauranteEntity()));

        ResponseEntity<?> response = controller.cadastrar(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }
}