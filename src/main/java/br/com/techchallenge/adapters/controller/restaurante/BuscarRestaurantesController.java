package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.BuscarRestaurantesUseCase;
import br.com.techchallenge.domain.Restaurante;
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
    public ResponseEntity<List<Restaurante>> buscarTodos() {
        try {
            List<Restaurante> restaurantes = buscarRestaurantesUseCase.buscarTodos();
            return ResponseEntity.ok(restaurantes);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar restaurantes", e);
        }
    }
}
