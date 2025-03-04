package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.adapters.converter.Restaurante.RestauranteDTOConverter;
import br.com.techchallenge.adapters.dto.donoRestaurante.DonoRestauranteSimplesResponseDto;
import br.com.techchallenge.adapters.dto.Restaurante.RestauranteRequestDTO;
import br.com.techchallenge.adapters.dto.Restaurante.RestauranteResponseDTO;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.service.RestauranteService;
import br.com.techchallenge.shared.exception.InternalServerErrorException;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity<List<RestauranteResponseDTO>> buscarTodos() {
        try {
            List<RestauranteEntity> restaurantes = service.buscarTodos();

            List<RestauranteResponseDTO> responseDtos = restaurantes.stream()
                    .map(restaurante -> new RestauranteResponseDTO(
                            restaurante.getId(),
                            restaurante.getNome(),
                            restaurante.getEndereco(),
                            restaurante.getTipoCozinha(),
                            restaurante.getDonoRestaurante() != null ? new DonoRestauranteSimplesResponseDto(
                                    restaurante.getDonoRestaurante().getNome(),
                                    restaurante.getDonoRestaurante().getEmail(),
                                    restaurante.getDonoRestaurante().getEndereco()

                            ) : null
                    )).toList();
            return ResponseEntity.ok(responseDtos);
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