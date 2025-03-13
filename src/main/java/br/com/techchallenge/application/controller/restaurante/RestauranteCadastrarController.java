package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoExisteException;
import br.com.techchallenge.domain.exception.RestauranteJaCadastradoException;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.restaurante.CadastrarRestauranteUseCase;
import br.com.techchallenge.infra.adapter.repository.restaurante.RestauranteCadastrarRepositoryImp;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteCadastrarController {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteDTOConverter restaurantesConverter;
    private final DonoRestauranteRepository donoRestauranteRepository;
    private final DonoRestauranteDTOConverter donoRestauranteConverter;

    public RestauranteCadastrarController(RestauranteRepository restauranteRepository, RestauranteDTOConverter restaurantesConverter, DonoRestauranteRepository donoRestauranteRepository, DonoRestauranteDTOConverter donoRestauranteConverter) {
        this.restauranteRepository = restauranteRepository;
        this.restaurantesConverter = restaurantesConverter;
        this.donoRestauranteRepository = donoRestauranteRepository;
        this.donoRestauranteConverter = donoRestauranteConverter;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody RestauranteRequestDTO request) {
        try {

            CadastrarRestauranteUseCase cadastrarRestauranteUseCase = new CadastrarRestauranteUseCase(
                    new RestauranteCadastrarRepositoryImp(
                            restauranteRepository,
                            donoRestauranteRepository,
                            restaurantesConverter,
                            donoRestauranteConverter));

            Restaurante restaurante = restaurantesConverter.dtoToRestaurante(request);
            Restaurante restauranteRetorno = cadastrarRestauranteUseCase.execute(restaurante);
            RestauranteListarTodosResponseDTO responseDTO = restaurantesConverter.restauranteParaResponseDto(restauranteRetorno);

            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RestauranteJaCadastradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (DonoRestauranteNaoExisteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Dono do Restaurante não localizado!");
        }
    }

}