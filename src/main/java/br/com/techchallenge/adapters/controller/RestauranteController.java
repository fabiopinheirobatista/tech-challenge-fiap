package br.com.techchallenge.adapters.controller;

import br.com.techchallenge.controller.converter.RestauranteDTOConverter;
import br.com.techchallenge.controller.dto.RestauranteRequestDTO;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.service.RestauranteService;
import br.com.techchallenge.shared.exception.InternalServerErrorException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody RestauranteRequestDTO request) {
        try {
            service.salvar(converter.converter(request), request.donoRestaurante());
            return new ResponseEntity<>("Restaurante cadastrado com sucesso", HttpStatus.CREATED);
        } catch (
                DataIntegrityViolationException e) {
            return new ResponseEntity<>("Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
        } catch (InternalServerErrorException e) {
            return new ResponseEntity<>("Dono de restaurante não encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws InternalServerErrorException {
        try {
            Optional<RestauranteEntity> restaurante = service.buscarPorId(id);
            if (restaurante.isPresent()) {
                return ResponseEntity.ok(restaurante.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não encontrado");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Erro ao buscar restaurante por id");
        }
    }

    @GetMapping
    public ResponseEntity<List<RestauranteEntity>> buscarTodos() {
        try {
            List<RestauranteEntity> restaurantes = service.buscarTodos();
            return ResponseEntity.ok(restaurantes);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar restaurantes", e);
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