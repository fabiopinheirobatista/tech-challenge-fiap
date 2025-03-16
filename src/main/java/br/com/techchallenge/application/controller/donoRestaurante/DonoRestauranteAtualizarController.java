package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteRequestDTO;
import br.com.techchallenge.domain.useCase.donoRestaurante.BuscarPorIdDonoRestauranteUseCase;
import br.com.techchallenge.domain.useCase.donoRestaurante.SalvarDonoRestauranteUseCase;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donos-restaurante")
@RequiredArgsConstructor
public class DonoRestauranteAtualizarController {

    private final DonoRestauranteDTOConverter converter;
    private final SalvarDonoRestauranteUseCase salvarDonoRestauranteUseCase;
    private final BuscarPorIdDonoRestauranteUseCase buscarPorIdDonoRestauranteUseCase;

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody DonoRestauranteRequestDTO request) {
        try {
            DonoRestauranteEntity existingDono = buscarPorIdDonoRestauranteUseCase.findById(id);
            if (existingDono == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não encontrado");
            }

            DonoRestauranteEntity updatedDono = converter.dtoParaEntity(id, request);
            updatedDono.setSenha(existingDono.getSenha());

            salvarDonoRestauranteUseCase.cadastrar(updatedDono);
            return ResponseEntity.ok("Dono de Restaurante atualizado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Dono de Restaurante");
        }
    }

}
