package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.techchallenge.infra.adapter.repository.restaurante.RestauranteBuscarPorIdRepositoryImp;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteBuscarPorIdController {


    private final RestauranteRepository restauranteRepository;
    private final RestauranteDTOConverter converter;

    public RestauranteBuscarPorIdController(RestauranteRepository restauranteRepository, RestauranteDTOConverter converter) {
        this.restauranteRepository = restauranteRepository;
        this.converter = converter;
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {//RestauranteBuscarTodosRepositoryImp
            BuscarRestaurantePorIdUseCase useCase = new BuscarRestaurantePorIdUseCase(new RestauranteBuscarPorIdRepositoryImp(restauranteRepository,converter));
            Optional<Restaurante> restauranteOptional = useCase.execute(id);

            if (restauranteOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante com o ID informado não foi encontrado");
            }

            RestauranteListarTodosResponseDTO responseDTO = converter.restauranteParaResponseDto(restauranteOptional.get());
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}