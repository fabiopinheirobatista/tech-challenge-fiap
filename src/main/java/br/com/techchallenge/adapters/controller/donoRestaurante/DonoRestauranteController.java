package br.com.techchallenge.adapters.controller.donoRestaurante;

import br.com.techchallenge.infra.dto.donoRestaurante.request.DonoRestauranteRequestDto;
import br.com.techchallenge.adapters.useCaseImpl.BuscarDonoRestaurantePorIdUseCase;
import br.com.techchallenge.adapters.useCaseImpl.CadastrarDonoRestauranteUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/donos-restaurante")
@RequiredArgsConstructor
public class DonoRestauranteController {

    private final CadastrarDonoRestauranteUseCase cadastrarDonoRestauranteUseCase;
    private final BuscarDonoRestaurantePorIdUseCase buscarDonoRestaurantePorIdUseCase;

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