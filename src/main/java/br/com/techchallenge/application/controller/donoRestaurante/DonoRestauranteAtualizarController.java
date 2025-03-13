package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteRequestDTO;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.DonoRestauranteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donos-restaurante")
public class DonoRestauranteAtualizarController {

    private final DonoRestauranteDTOConverter converter;
    private final DonoRestauranteService service;

    public DonoRestauranteAtualizarController(DonoRestauranteDTOConverter converter, DonoRestauranteService service) {
        this.converter = converter;
        this.service = service;
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody DonoRestauranteRequestDTO request) {
        try {
            DonoRestauranteEntity existingDono = service.buscarPorId(id);
            if (existingDono == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não encontrado");
            }

            DonoRestauranteEntity updatedDono = converter.dtoParaEntity(id, request);
            updatedDono.setSenha(existingDono.getSenha());

            service.salvar(updatedDono);
            return ResponseEntity.ok("Dono de Restaurante atualizado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Dono de Restaurante");
        }
    }

}
