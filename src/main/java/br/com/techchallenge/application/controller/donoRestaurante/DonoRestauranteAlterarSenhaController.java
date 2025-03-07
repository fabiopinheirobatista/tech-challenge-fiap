package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteAlterarSenhaRequestDTO;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donos-restaurante")
public class DonoRestauranteAlterarSenhaController {

    private final DonoRestauranteDTOConverter converter;
    private final DonoRestauranteService service;

    public DonoRestauranteAlterarSenhaController(DonoRestauranteDTOConverter converter, DonoRestauranteService service) {
        this.converter = converter;
        this.service = service;
    }

    @PutMapping("/alterar-senha")
    public ResponseEntity<String> alterarSenha(@RequestBody DonoRestauranteAlterarSenhaRequestDTO request) {
        boolean atualizado = service.alterarSenha(request.getId(), request.getEmail(), request.getSenhaAtual(), request.getNovaSenha());
        if (!atualizado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atualização não realizada pois o ID informado não foi localizado ou o email/senha estão incorretos!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
    }

}
