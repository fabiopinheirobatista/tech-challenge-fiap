package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.BuscarRestaurantesUseCase;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.donoRestaurante.response.DonoRestauranteSimplesResponseDto;
import br.com.techchallenge.infra.dto.restaurante.response.RestauranteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurante")
@RequiredArgsConstructor
public class BuscarRestaurantesController {

    private final BuscarRestaurantesUseCase buscarRestaurantesUseCase;

    @GetMapping
    public ResponseEntity<List<RestauranteResponseDto>> buscarTodos() {
        try {
            List<Restaurante> restaurantes = buscarRestaurantesUseCase.buscarTodos();

            List<RestauranteResponseDto> responseDtos = restaurantes.stream()
                    .map(restaurante -> new RestauranteResponseDto(
                            restaurante.getId(),
                            restaurante.getNome(),
                            restaurante.getEndereco(),
                            restaurante.getTipoCozinha(),
                            restaurante.getDonoRestaurante() != null ? new DonoRestauranteSimplesResponseDto(
                                    restaurante.getDonoRestaurante().getNome(),
                                    restaurante.getDonoRestaurante().getEmail(),
                                    restaurante.getDonoRestaurante().getEndereco()

                            ) : null
                    )).toList();

            return ResponseEntity.ok(responseDtos);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar restaurantes", e);
        }
    }
}
