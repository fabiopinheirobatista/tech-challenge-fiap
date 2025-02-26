package br.com.techchallenge.adapters.controller.donoRestaurante;

import br.com.techchallenge.adapters.useCaseImpl.donoRestaurante.DonoRestauranteAtualizarUseCase;
import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donos-restaurante")
@RequiredArgsConstructor
public class DonoRestauranteControllerAtualizar {

    private final DonoRestauranteAtualizarUseCase donoRestauranteAtualizarUseCase;
    private final RestauranteMapper mapper;

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Restaurante request) {
        try {
            request.setId(id);
            donoRestauranteAtualizarUseCase.atualizar(request.getDonoRestaurante());
            return ResponseEntity.ok("Dono de Restaurante atualizado com sucesso");

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Dono de Restaurante");
        }
    }
}
