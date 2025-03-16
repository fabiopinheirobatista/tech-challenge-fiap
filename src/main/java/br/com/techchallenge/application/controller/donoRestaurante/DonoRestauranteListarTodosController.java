package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteListarTodosResponseDTO;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/donos-restaurante")
@RequiredArgsConstructor
public class DonoRestauranteListarTodosController {

    private final DonoRestauranteDTOConverter converter;
    private final DonoRestauranteService service;

    @GetMapping("/listar-todos")
    public ResponseEntity<?> buscarTodos() {
        try {
            List<DonoRestauranteEntity> donos = service.buscarTodos();
            if (donos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Não existem Donos de Restaurante cadastrados.");
            }
            List<DonoRestauranteListarTodosResponseDTO> response = donos.stream()
                    .sorted(Comparator.comparing(DonoRestauranteEntity::getId))
                    .map(converter::entityParaListarTodosDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
