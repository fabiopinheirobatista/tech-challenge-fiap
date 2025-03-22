package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteAlterarSenhaRequestDTO;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DonoRestauranteAlterarSenhaControllerTest {

    @Mock
    private DonoRestauranteService service;

    @Mock
    private DonoRestauranteDTOConverter converter;

    @InjectMocks
    private DonoRestauranteAlterarSenhaController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void alterarSenha_ComDadosInvalidos_RetornaErro() {
        DonoRestauranteAlterarSenhaRequestDTO request = new DonoRestauranteAlterarSenhaRequestDTO(null, "email@teste.com", "senhaAtual", "novaSenha");

        when(service.alterarSenha(any(), any(String.class), any(String.class), any(String.class)))
                .thenReturn(false);

        ResponseEntity<String> response = controller.alterarSenha(request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Atualização não realizada pois o ID informado não foi localizado ou o email/senha estão incorretos!", response.getBody());
    }

    @Test
    void alterarSenha_ComCamposVazios_RetornaErro() {
        DonoRestauranteAlterarSenhaRequestDTO request = new DonoRestauranteAlterarSenhaRequestDTO(1L, "", "", "novaSenha");

        when(service.alterarSenha(any(Long.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(false);

        ResponseEntity<String> response = controller.alterarSenha(request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Atualização não realizada pois o ID informado não foi localizado ou o email/senha estão incorretos!", response.getBody());
    }

    @Test
    void alterarSenha_ComDadosValidos_RetornaSucesso() {
        DonoRestauranteAlterarSenhaRequestDTO request = new DonoRestauranteAlterarSenhaRequestDTO(1L, "email@teste.com", "senhaAtual", "novaSenha");

        when(service.alterarSenha(eq(1L), eq("email@teste.com"), eq("senhaAtual"), eq("novaSenha")))
                .thenReturn(true);

        ResponseEntity<String> response = controller.alterarSenha(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Atualização realizada com sucesso!", response.getBody());
        verify(service).alterarSenha(eq(1L), eq("email@teste.com"), eq("senhaAtual"), eq("novaSenha"));
    }

}