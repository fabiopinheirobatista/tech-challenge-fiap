package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteValidarLoginRequestDTO;
import br.com.techchallenge.domain.useCase.donoRestaurante.ValidarLoginDonoRestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donos-restaurante")
@RequiredArgsConstructor
public class DonoRestauranteValidarLoginController {

    private final ValidarLoginDonoRestauranteUseCase validarLoginDonoRestauranteUseCase;

    @PostMapping("/validar-login")
    public ResponseEntity<String> validarLogin(@RequestBody DonoRestauranteValidarLoginRequestDTO request) {
        ValidarLoginDonoRestauranteUseCase.ResultadoValidacao resultado =
                validarLoginDonoRestauranteUseCase.execute(request);

        switch (resultado) {
            case USUARIO_NAO_ENCONTRADO:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Usuário inexistente!");
            case CREDENCIAIS_INVALIDAS:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Usuário/senha inválidos!");
            case SUCESSO:
                return ResponseEntity.ok("Usuário validado com sucesso!");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao validar usuário!");
        }
    }
}