package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteValidarLoginRequestDTO;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.service.DonoRestauranteService;
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

    private final DonoRestauranteDTOConverter converter;
    private final DonoRestauranteService service;

    @PostMapping("/validar-login")
    public ResponseEntity<String> validarLogin(@RequestBody DonoRestauranteValidarLoginRequestDTO request) {
        DonoRestauranteEntity dono = service.buscarPorId(request.getId());
        if (dono == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário inexistente!");
        }

        boolean isValid = service.validarLogin(request.getId(), request.getLogin(), request.getSenha());
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário/senha inválidos!");
        }

        return ResponseEntity.ok("Usuário validado com sucesso!");
    }

}
