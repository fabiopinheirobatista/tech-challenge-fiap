package br.com.techchallenge.adapters.controller.donoRestaurante;

import br.com.techchallenge.infra.dto.donoRestaurante.request.DonoRestauranteRequestDto;
import br.com.techchallenge.application.BuscarDonoRestaurantePorIdUseCase;
import br.com.techchallenge.application.CadastrarDonoRestauranteUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/donos-restaurante")
public class DonoRestauranteController {

    private final CadastrarDonoRestauranteUseCase cadastrarDonoRestauranteUseCase;
    private final BuscarDonoRestaurantePorIdUseCase buscarDonoRestaurantePorIdUseCase;

    public DonoRestauranteController(CadastrarDonoRestauranteUseCase cadastrarDonoRestauranteUseCase,
                                     BuscarDonoRestaurantePorIdUseCase buscarDonoRestaurantePorIdUseCase) {
        this.cadastrarDonoRestauranteUseCase = cadastrarDonoRestauranteUseCase;
        this.buscarDonoRestaurantePorIdUseCase = buscarDonoRestaurantePorIdUseCase;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody DonoRestauranteRequestDto request) {
        try {
            DonoRestaurante dono = new DonoRestaurante(
                    request.nome(),
                    request.endereco(),
                    request.email(),
                    request.login(),
                    request.senha(),
                    LocalDate.now()
            );

            DonoRestaurante donoSalvo = cadastrarDonoRestauranteUseCase.cadastrar(dono);
            return new ResponseEntity<>("Dono de Restaurante cadastrado com sucesso", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Dono de Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
        }
    }
}