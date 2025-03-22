package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteRequestDTO;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DonoRestauranteAtualizarControllerTest {

    @Mock
    private DonoRestauranteService service;

    @Mock
    private DonoRestauranteDTOConverter converter;

    @InjectMocks
    private DonoRestauranteAtualizarController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void atualizar_ComDonoNaoEncontrado_RetornaErro() {
        Long id = 999L;
        DonoRestauranteRequestDTO requestDTO = new DonoRestauranteRequestDTO(1L, "email@teste.com", new Endereco(), "telefone", "senha");

        when(service.buscarPorId(id)).thenReturn(null);

        ResponseEntity<String> response = controller.atualizar(id, requestDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Dono de Restaurante não encontrado", response.getBody());
    }

}