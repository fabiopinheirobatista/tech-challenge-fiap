package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteAlterarSenhaRequestDTO;
import br.com.techchallenge.domain.useCase.donoRestaurante.AlterarSenhaDonoRestauranteUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteAlterarSenhaControllerTest {

    @Mock
    private AlterarSenhaDonoRestauranteUseCase alterarSenhaUseCase;

    @InjectMocks
    private DonoRestauranteAlterarSenhaController controller;

    @Test
    @DisplayName("Deve retornar erro quando os dados são inválidos")
    void deveRetornarErroQuandoDadosInvalidos() {
        DonoRestauranteAlterarSenhaRequestDTO request = new DonoRestauranteAlterarSenhaRequestDTO(1L, "email@teste.com", "senhaErrada", "novaSenha");

        when(alterarSenhaUseCase.alterarSenha(eq(1L), eq("email@teste.com"), eq("senhaErrada"), eq("novaSenha")))
                .thenReturn(false);

        ResponseEntity<String> response = controller.alterarSenha(request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Atualização não realizada pois o ID informado não foi localizado ou o email/senha estão incorretos!", response.getBody());
        verify(alterarSenhaUseCase).alterarSenha(eq(1L), eq("email@teste.com"), eq("senhaErrada"), eq("novaSenha"));
    }

    @Test
    @DisplayName("Deve retornar sucesso quando os dados são válidos")
    void deveRetornarSucessoQuandoDadosValidos() {
        DonoRestauranteAlterarSenhaRequestDTO request = new DonoRestauranteAlterarSenhaRequestDTO(1L, "email@teste.com", "senhaAtual", "novaSenha");

        when(alterarSenhaUseCase.alterarSenha(eq(1L), eq("email@teste.com"), eq("senhaAtual"), eq("novaSenha")))
                .thenReturn(true);

        ResponseEntity<String> response = controller.alterarSenha(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Atualização realizada com sucesso!", response.getBody());
        verify(alterarSenhaUseCase).alterarSenha(eq(1L), eq("email@teste.com"), eq("senhaAtual"), eq("novaSenha"));
    }
}