package br.com.techchallenge.application.controller.itemCardapio;

import br.com.techchallenge.application.controller.itensCardapio.ItensCardapioBuscarPorIdController;
import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.output.itensCardapio.ItensCardapioResponseDTO;
import br.com.techchallenge.domain.useCase.itensCardapio.BuscarPorIdItensCardapioUseCase;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
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
class ItensCardapioBuscarPorIdControllerTest {

    @Mock
    private BuscarPorIdItensCardapioUseCase useCase;

    @Mock
    private ItensCardapioDTOConverter converter;

    @InjectMocks
    private ItensCardapioBuscarPorIdController itensCardapioBuscarPorIdController;

    @Test
    void deveRetornar200QuandoItemEncontrado() {
        ItensCardapio item = new ItensCardapio(1L, "Pizza", "Descrição", 29.90, "disponível", "url_foto", 1L);
        ItensCardapioResponseDTO itemResponseDTO = new ItensCardapioResponseDTO(1L, "Pizza", "Descrição", 29.90, "disponível", "url_foto", 1L);

        when(useCase.execute(1L)).thenReturn(Optional.of(item));
        when(converter.domainToDto(item)).thenReturn(itemResponseDTO);

        ResponseEntity<?> response = itensCardapioBuscarPorIdController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(itemResponseDTO, response.getBody());
    }

    @Test
    void deveRetornar404QuandoItemNaoEncontrado() {
        when(useCase.execute(1L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = itensCardapioBuscarPorIdController.buscarPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Item do Cardapio com o ID informado não foi encontrado", response.getBody());
    }

}
