package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.techchallenge.domain.useCase.restaurante.ExcluirRestauranteUseCase;
import br.com.techchallenge.infra.adpter.repository.RestauranteBuscarPorIdRepositoryImp;
import br.com.techchallenge.infra.adpter.repository.RestauranteDeletarRepositoryImp;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteDeletarController {


    private final RestauranteRepository restauranteRepository;
    private final RestauranteDTOConverter converter;

    public RestauranteDeletarController(RestauranteRepository restauranteRepository, RestauranteDTOConverter converter) {
        this.restauranteRepository = restauranteRepository;
        this.converter = converter;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        try {


            ExcluirRestauranteUseCase useCase = new ExcluirRestauranteUseCase(
                    new RestauranteDeletarRepositoryImp(restauranteRepository,converter),
                    new RestauranteBuscarPorIdRepositoryImp(restauranteRepository,converter));
            useCase.execute(id);

            return new ResponseEntity<>("Restaurante excluído com sucesso", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar restaurante", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RestauranteNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não localizado!");
        }
    }
    /*
    public ResponseEntity<String> deletar() {
        try {
            Optional<RestauranteEntity> restauranteOptional = service.buscarPorId(id);
            if (restauranteOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não localizado!");
            }

            service.deletar(id);
            return new ResponseEntity<>("Restaurante excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar restaurante", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

}