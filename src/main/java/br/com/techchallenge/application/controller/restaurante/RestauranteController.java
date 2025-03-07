package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.application.converter.Restaurante.RestauranteDTOConverter;
import br.com.techchallenge.domain.dto.Restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.dto.Restaurante.RestauranteRequestDTO;
import br.com.techchallenge.domain.dto.Restaurante.RestauranteResponseDTO;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.service.RestauranteService;
import br.com.techchallenge.shared.exception.InternalServerErrorException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteController {

    private final RestauranteService service;
    private final RestauranteDTOConverter converter;

    public RestauranteController(RestauranteService service, RestauranteDTOConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody RestauranteRequestDTO request) {
        try {
            service.salvar(converter.dtoParaEntity(request), request.donoRestaurante());
            return new ResponseEntity<>("Restaurante cadastrado com sucesso", HttpStatus.CREATED);
        } catch (
                DataIntegrityViolationException e) {
            return new ResponseEntity<>("Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws InternalServerErrorException {
        try {
            Optional<RestauranteEntity> restaurante = service.buscarPorId(id);
            if (restaurante.isPresent()) {
                RestauranteResponseDTO responseDTO = converter.entityParaResponseDto(restaurante.get());
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não encontrado");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Erro ao buscar restaurante por id");
        }
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<?> buscarTodos() {
        try {
            List<RestauranteEntity> restaurantes = service.buscarTodos();
            if (restaurantes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Não existem Restaurantes cadastrados.");
            }
            List<RestauranteListarTodosResponseDTO> response = restaurantes.stream()
                    .sorted(Comparator.comparing(RestauranteEntity::getId))
                    .map(converter::entityParaListarTodosDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return new ResponseEntity<>("Restaurante deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar restaurante", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}