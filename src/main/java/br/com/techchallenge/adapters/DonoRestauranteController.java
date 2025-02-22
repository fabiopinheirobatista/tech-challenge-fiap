package br.com.techchallenge.adapters;

import br.com.techchallenge.adapters.response.DonoRestauranteResponse;
import br.com.techchallenge.application.BuscarDonoRestaurantePorIdUseCase;
import br.com.techchallenge.application.CadastrarDonoRestauranteUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

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
    public ResponseEntity<String> cadastrar(@RequestBody DonoRestauranteRequest request) {
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

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<DonoRestaurante> donoOptional = buscarDonoRestaurantePorIdUseCase.buscarPorId(id);

        if (donoOptional.isPresent()) {
            DonoRestaurante dono = donoOptional.get();
            String message = String.format("Dono de Restaurante com o ID %d localizado com sucesso.", id);
            DonoRestauranteResponse response = new DonoRestauranteResponse(
                    message, dono.getNome(), dono.getEndereco(), dono.getEmail(), dono.getLogin()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            String mensagem = String.format("Dono de Restaurante com o ID %d não foi localizado.", id);
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
    }
}