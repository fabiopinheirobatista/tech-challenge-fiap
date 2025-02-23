package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.DeleteRestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurante")
@RequiredArgsConstructor
public class DeleteRestauranteController {

    private final DeleteRestauranteUseCase restauranteDeletarUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            restauranteDeletarUseCase.deletar(id);
            return new ResponseEntity<>("Restaurante deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar restaurante", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
