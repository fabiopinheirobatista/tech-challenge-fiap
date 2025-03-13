package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.infra.converter.Restaurante.RestauranteDTOConverter;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarIdResponseDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import br.com.techchallenge.infra.service.RestauranteService;
import jakarta.persistence.EntityNotFoundException;
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
    private final DonoRestauranteService donoRestauranteService;
    private final RestauranteDTOConverter converter;

    public RestauranteController(RestauranteService service, DonoRestauranteService donoRestauranteService, RestauranteDTOConverter converter) {
        this.service = service;
        this.donoRestauranteService = donoRestauranteService;
        this.converter = converter;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody RestauranteRequestDTO request) {
        try {
            if (service.nomeRestauranteExiste(request.getNome())) {
                return new ResponseEntity<>("Restaurante já cadastrado com esse nome!", HttpStatus.CONFLICT);
            }

            Optional<DonoRestauranteEntity> donoRestaurante = Optional.ofNullable(donoRestauranteService.buscarPorId(request.getIdDonoRestaurante()));
            if (donoRestaurante.isEmpty()) {
                return new ResponseEntity<>("Dono de Restaurante informado não existe!", HttpStatus.BAD_REQUEST);
            }

            RestauranteEntity restaurante = converter.dtoParaEntity(request);
            restaurante.setDonoRestaurante(donoRestaurante.get());
            service.salvar(restaurante);
            return new ResponseEntity<>("Cadastro realizado com sucesso", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody RestauranteRequestDTO request) {
        try {
            RestauranteEntity restaurante = service.buscarPorId(id)
                    .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));

            restaurante.setNome(request.getNome());
            restaurante.setEndereco(request.endereco());
            restaurante.setTipoCozinha(request.tipoCozinha());

            service.salvar(restaurante);
            return ResponseEntity.ok("Restaurante atualizado com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar restaurante");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Optional<RestauranteEntity> restaurante = service.buscarPorId(id);
            if (restaurante.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante com o ID informado não foi encontrado");
            }
            RestauranteListarIdResponseDTO responseDTO = converter.entityParaListarIdDto(restaurante.get());
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar restaurante por id");
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
                    .map(restaurante -> new RestauranteListarTodosResponseDTO(
                            restaurante.getId(),
                            restaurante.getNome(),
                            restaurante.getEndereco().toString(),
                            restaurante.getTipoCozinha()
                    ))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            Optional<RestauranteEntity> restauranteOptional = service.buscarPorId(id);
            if (restauranteOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não localizado!");
            }

            service.deletar(id);
            return new ResponseEntity<>("Restaurante excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar restaurante", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}