package br.com.techchallenge.adapters;

import br.com.techchallenge.application.CadastrarDonoRestauranteUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/donos-restaurante")
public class DonoRestauranteController {

    private final CadastrarDonoRestauranteUseCase cadastrarDonoRestauranteUseCase;

    public DonoRestauranteController(CadastrarDonoRestauranteUseCase cadastrarDonoRestauranteUseCase) {
        this.cadastrarDonoRestauranteUseCase = cadastrarDonoRestauranteUseCase;
    }

    @PostMapping
    public ResponseEntity<DonoRestaurante> cadastrar(@RequestBody DonoRestauranteRequest request) {
        DonoRestaurante dono = new DonoRestaurante(
                request.getNome(),
                request.getEndereco(),
                request.getEmail(),
                request.getLogin(),
                request.getSenha(),
                LocalDate.now()
        );

        DonoRestaurante donoSalvo = cadastrarDonoRestauranteUseCase.cadastrar(dono);
        return new ResponseEntity<>(donoSalvo, HttpStatus.CREATED);
    }
}