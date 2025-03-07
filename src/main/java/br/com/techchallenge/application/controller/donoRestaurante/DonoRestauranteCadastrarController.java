package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteCadastrarRequestDTO;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donos-restaurante")
public class DonoRestauranteCadastrarController {

    private final DonoRestauranteDTOConverter converter;
    private final DonoRestauranteService service;

    public DonoRestauranteCadastrarController(DonoRestauranteDTOConverter converter, DonoRestauranteService service) {
        this.converter = converter;
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody DonoRestauranteCadastrarRequestDTO request) {
        if (service.donoRestauranteExiste(request.getEmail(), request.getLogin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cadastro não realizado pois já existe um registro com esse email/login!");
        }
        service.salvar(converter.dtoComSenhaParaEntity(request));
        return new ResponseEntity<>("Dono de Restaurante cadastrado com sucesso", HttpStatus.CREATED);
    }

}
