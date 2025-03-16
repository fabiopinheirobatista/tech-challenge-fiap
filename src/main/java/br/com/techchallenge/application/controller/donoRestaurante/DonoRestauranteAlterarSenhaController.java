package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteAlterarSenhaRequestDTO;
import br.com.techchallenge.domain.useCase.donoRestaurante.AlterarSenhaDonoRestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donos-restaurante")
@RequiredArgsConstructor
public class DonoRestauranteAlterarSenhaController {

    private final AlterarSenhaDonoRestauranteUseCase alterarSenhaUseCase;

    @PutMapping("/alterar-senha")
    public ResponseEntity<String> alterarSenha(@RequestBody DonoRestauranteAlterarSenhaRequestDTO request) {
        boolean atualizado = alterarSenhaUseCase.alterarSenha(
                request.getId(),
                request.getEmail(),
                request.getSenhaAtual(),
                request.getNovaSenha());
        if (!atualizado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Atualização não realizada pois o ID informado não foi localizado ou o email/senha estão incorretos!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
    }

}
