package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.adapters.useCaseImpl.restaurante.RestauranteCadastrarUseCase;
import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.restaurante.request.RestauranteRequestDto;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurante")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteCadastrarUseCase restauranteCadastrarUseCase;
    private final RestauranteMapper mapper;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody RestauranteRequestDto request) {
        try {
            Restaurante restaurante = new Restaurante(
                    request.nome(),
                    request.endereco(),
                    request.tipoCozinha()
            );

            RestauranteEntity entity = mapper.toRestauranteEntity(restaurante);

            Restaurante restauranteSalvo = restauranteCadastrarUseCase.cadastrar(restaurante);

            return new ResponseEntity<>("Restaurante cadastrado com sucesso", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
        }
    }
}