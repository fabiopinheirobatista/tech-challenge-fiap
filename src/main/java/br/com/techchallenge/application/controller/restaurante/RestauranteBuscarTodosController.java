package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.restaurante.BuscarTodosRestauranteUseCase;
import br.com.techchallenge.infra.adpter.repository.RestauranteBuscarTodosRepositoryImp;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteBuscarTodosController {


    private final RestauranteRepository restauranteRepository;
    private final RestauranteDTOConverter converter;

    public RestauranteBuscarTodosController(RestauranteRepository restauranteRepository, RestauranteDTOConverter converter) {
        this.restauranteRepository = restauranteRepository;
        this.converter = converter;
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<?> buscarTodos() {
        try {
            BuscarTodosRestauranteUseCase useCase = new BuscarTodosRestauranteUseCase(new RestauranteBuscarTodosRepositoryImp(restauranteRepository,converter));
            List<Restaurante> restaurantes = useCase.execute();

            if (restaurantes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Não existem Restaurantes cadastrados.");
            }

            List<RestauranteListarTodosResponseDTO> response = restaurantes.stream()
                    .map(restaurante -> new RestauranteListarTodosResponseDTO(
                            restaurante.getId(),
                            restaurante.getNome(),
                            restaurante.getEndereco() != null ? restaurante.getEndereco() : null,
                            restaurante.getTipoCozinha()
                    ))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}