package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.BuscarRestaurantesUseCase;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.restaurante.response.RestauranteResponseDto;
import lombok.RequiredArgsConstructor;
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
    public List<Restaurante> buscarTodos() {
        return buscarRestaurantesUseCase.buscarTodos();
    }
}
