package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.restaurante.AtualizarRestauranteUseCase;
import br.com.techchallenge.infra.adapter.repository.restaurante.RestauranteAtualizarRepositoryImp;
import br.com.techchallenge.infra.adapter.repository.restaurante.RestauranteBuscarPorIdRepositoryImp;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteAlterarController {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteDTOConverter converter;


    public RestauranteAlterarController(RestauranteRepository restauranteRepository, RestauranteDTOConverter converter) {
        this.restauranteRepository = restauranteRepository;
        this.converter = converter;
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody RestauranteRequestDTO request) {
        try {
            Restaurante restaurante1 = converter.dtoToRestaurante(request);

            AtualizarRestauranteUseCase useCase = new AtualizarRestauranteUseCase(
                    new RestauranteAtualizarRepositoryImp(restauranteRepository,converter),
                    new RestauranteBuscarPorIdRepositoryImp(restauranteRepository,converter)
            );
            restaurante1.setId(id);
            Restaurante restaurante = useCase.execute(id, restaurante1);
            RestauranteListarTodosResponseDTO responseDTO = converter.restauranteParaResponseDto(restaurante);
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar restaurante");
        } catch (RestauranteNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não encontrado");
        }
    }


}