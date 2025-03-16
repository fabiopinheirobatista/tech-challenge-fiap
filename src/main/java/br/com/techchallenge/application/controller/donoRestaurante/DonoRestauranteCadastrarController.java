package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteCadastrarRequestDTO;
import br.com.techchallenge.domain.useCase.donoRestaurante.SalvarDonoRestauranteUseCase;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
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
public class DonoRestauranteCadastrarController {

    private final DonoRestauranteDTOConverter converter;
    private final SalvarDonoRestauranteUseCase salvarDonoRestauranteUseCase;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody DonoRestauranteCadastrarRequestDTO request) {
        boolean cadastrado = salvarDonoRestauranteUseCase.cadastrar(converter.dtoComSenhaParaEntity(request));
        if (!cadastrado) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cadastro não realizado pois já existe um registro com esse email/login!");
        }
        return new ResponseEntity<>("Dono de Restaurante cadastrado com sucesso", HttpStatus.CREATED);
    }

}
