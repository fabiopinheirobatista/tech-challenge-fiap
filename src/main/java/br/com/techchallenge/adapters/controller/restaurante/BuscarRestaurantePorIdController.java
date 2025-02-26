package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.donoRestaurante.response.DonoRestauranteSimplesResponseDto;
import br.com.techchallenge.infra.dto.restaurante.response.RestauranteResponseDto;
import br.com.techchallenge.shared.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/restaurante")
@RequiredArgsConstructor
public class BuscarRestaurantePorIdController {

    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws InternalServerErrorException {
        try {
            Optional<Restaurante> restaurante = buscarRestaurantePorIdUseCase.buscarPorId(id);
            if (restaurante.isPresent()) {

                RestauranteResponseDto responseDto = new RestauranteResponseDto(
                        restaurante.get().getId(),
                        restaurante.get().getNome(),
                        restaurante.get().getEndereco(),
                        restaurante.get().getTipoCozinha(),
                        restaurante.get().getDonoRestaurante() != null ? new DonoRestauranteSimplesResponseDto(
                                restaurante.get().getDonoRestaurante().getNome(),
                                restaurante.get().getDonoRestaurante().getEmail(),
                                restaurante.get().getDonoRestaurante().getEndereco()
                        ) : null
                );

                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não encontrado");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Erro ao buscar restaurante por id");
        }
    }
}
