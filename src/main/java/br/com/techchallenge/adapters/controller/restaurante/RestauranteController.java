package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/restaurante")
@RequiredArgsConstructor
public class RestauranteController {

    //private final CreateRestauranteUseCase createRestauranteUseCase;
    private final RestauranteMapper restauranteMapper;


//    @PostMapping("/cadastrar")
//    public ResponseEntity<?> createRestaurante(@RequestBody RestauranteRequestDto restauranteRequestDto) throws InternalServerErrorException {
//        createRestauranteUseCase.createRestaurante(restauranteMapper.toRestauranteRequestDto(restauranteRequestDto));
//        return ResponseEntity.status(HttpStatus.CREATED).body("Restaurante cadastrado com sucesso");
//
//    }
}
