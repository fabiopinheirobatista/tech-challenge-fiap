package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.RestauranteSalvarInterface;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.domain.useCase.restaurante.CadastrarRestauranteUseCase;
import br.com.techchallenge.infra.adpter.repository.RestauranteCadastrarRepositoryImp;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteCadastrarController {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteDTOConverter converter;

    public RestauranteCadastrarController(RestauranteRepository restauranteRepository, RestauranteDTOConverter converter) {
        this.restauranteRepository = restauranteRepository;
        this.converter = converter;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody RestauranteRequestDTO request) {
        try {

            CadastrarRestauranteUseCase cadastrarRestauranteUseCase = new CadastrarRestauranteUseCase(new RestauranteCadastrarRepositoryImp(restauranteRepository,converter));
            Restaurante restaurante = converter.dtoToRestaurante(request);
            cadastrarRestauranteUseCase.execute(restaurante);

            return new ResponseEntity<>("Cadastro realizado com sucesso", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}