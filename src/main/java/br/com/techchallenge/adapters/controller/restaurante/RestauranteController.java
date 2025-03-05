package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.adapters.converter.Restaurante.RestauranteDTOConverter;
import br.com.techchallenge.adapters.dto.Restaurante.RestauranteListarIdResponseDTO;
import br.com.techchallenge.adapters.dto.Restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.adapters.dto.Restaurante.RestauranteRequestDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import br.com.techchallenge.infra.service.RestauranteService;
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

            service.salvar(converter.dtoParaEntity(request), request.donoRestaurante());
            return new ResponseEntity<>("Restaurante cadastrado com sucesso", HttpStatus.CREATED);
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

            restaurante.setNome(request.nome());
            restaurante.setEndereco(request.endereco());
            restaurante.setTipoCozinha(request.tipoCozinha());

            service.salvar((restaurante), request.donoRestaurante());
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

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody RestauranteRequestDTO request) {
        try {
            Optional<RestauranteEntity> restauranteOptional = service.buscarPorId(id);
            if (restauranteOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não localizado!");
            }

            Optional<DonoRestauranteEntity> donoRestauranteOptional = Optional.ofNullable(donoRestauranteService.buscarPorId(request.donoRestaurante()));
            if (donoRestauranteOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dono de Restaurante não localizado!");
            }

            RestauranteEntity restaurante = restauranteOptional.get();
            restaurante.setNome(request.nome());
            restaurante.setEndereco(request.endereco());
            restaurante.setTipoCozinha(request.tipoCozinha());
            restaurante.setDonoRestaurante(donoRestauranteOptional.get());

            service.salvar(restaurante, request.donoRestaurante());

            return ResponseEntity.ok("Alteração realizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar restaurante");
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