package br.com.techchallenge.adapters.controller.restaurante;


import br.com.techchallenge.adapters.UseCaseImpl.restaurante.AtualizarRestauranteUseCase;
import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.restaurante.request.RestauranteRequestDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurante")
@RequiredArgsConstructor
public class AtualizaRestauranteController {

    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final RestauranteMapper mapper;

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Restaurante request) {
        try {
            request.setId(id);
            atualizarRestauranteUseCase.atualizar(request);
            return ResponseEntity.ok("Restaurante atualizado com sucesso");

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar restaurante");
        }
    }
}
