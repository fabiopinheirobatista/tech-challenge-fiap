package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteListarIdResponseDTO;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donos-restaurante")
public class DonoRestauranteListarPorIdController {

    private final DonoRestauranteDTOConverter converter;
    private final DonoRestauranteService service;

    public DonoRestauranteListarPorIdController(DonoRestauranteDTOConverter converter, DonoRestauranteService service) {
        this.converter = converter;
        this.service = service;
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            DonoRestauranteEntity dono = service.buscarPorId(id);
            DonoRestauranteListarIdResponseDTO dto = converter.entityParaListarIdDto(dono);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não encontrado");
        }
    }


}
