package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.useCase.restaurante.ExcluirRestauranteUseCase;
import br.com.techchallenge.infra.adapter.repository.restaurante.RestauranteBuscarPorIdRepositoryImp;
import br.com.techchallenge.infra.adapter.repository.restaurante.RestauranteDeletarRepositoryImp;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteDeletarController {


    private final RestauranteRepository restauranteRepository;
    private final RestauranteDTOConverter converter;
    private final ExcluirRestauranteUseCase useCase;

    public RestauranteDeletarController(RestauranteRepository restauranteRepository, RestauranteDTOConverter converter, ExcluirRestauranteUseCase useCase) {
        this.restauranteRepository = restauranteRepository;
        this.converter = converter;
        this.useCase = useCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) throws RestauranteNaoEncontradoException {

        useCase.execute(id);
        return new ResponseEntity<>("Restaurante excluído com sucesso", HttpStatus.OK);

    }

}