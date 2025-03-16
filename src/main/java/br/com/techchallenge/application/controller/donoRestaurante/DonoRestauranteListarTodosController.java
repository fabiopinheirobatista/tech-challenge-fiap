package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.donoRestaurante.BuscarTodosDonoRestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/donos-restaurante")
@RequiredArgsConstructor
public class DonoRestauranteListarTodosController {

    private final BuscarTodosDonoRestauranteUseCase buscarTodosDonoRestauranteUseCase;

    @GetMapping("/listar-todos")
    public ResponseEntity<?> buscarTodos() {
        try {
            List<DonoRestauranteListarTodosResponseDTO> response = buscarTodosDonoRestauranteUseCase.execute();
            if (response.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Não existem Donos de Restaurante cadastrados.");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
