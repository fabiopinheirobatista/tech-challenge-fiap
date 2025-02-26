package br.com.techchallenge.adapters.controller.donoRestaurante;

import br.com.techchallenge.adapters.converter.DonoRestauranteDTOConverter;
import br.com.techchallenge.adapters.dto.donoRestaurante.DonoRestauranteRequestDTO;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donos-restaurante")
public class DonoRestauranteController {

    private final DonoRestauranteDTOConverter converter;
    private final DonoRestauranteService service;

    public DonoRestauranteController(DonoRestauranteDTOConverter converter, DonoRestauranteService service) {
        this.converter = converter;
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody DonoRestauranteRequestDTO request) {
        try {
            service.salvar(converter.dtoParaEntity(request));
            return new ResponseEntity<>("Dono de Restaurante cadastrado com sucesso", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Dono de Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody DonoRestauranteRequestDTO request) {
        try {
            service.salvar(converter.dtoParaEntity(id,request));
            return ResponseEntity.ok("Dono de Restaurante atualizado com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Dono de Restaurante");
        }
    }

}
