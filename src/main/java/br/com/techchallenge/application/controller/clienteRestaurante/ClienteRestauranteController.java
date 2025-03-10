package br.com.techchallenge.application.controller.clienteRestaurante;

import br.com.techchallenge.domain.input.clienteRestaurante.ClienteRestauranteRequestDto;
import br.com.techchallenge.domain.output.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.infra.converter.clienteRestaurante.ClienteRestauranteDtoConverter;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.service.ClienteRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cliente-restaurante")
@RequiredArgsConstructor
public class ClienteRestauranteController {


    private final ClienteRestauranteService clienteRestauranteService;
    private final ClienteRestauranteDtoConverter converter;


    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ClienteRestauranteRequestDto request) {
        try {
            clienteRestauranteService.salvar(converter.dtoParaEntity(request));
            return ResponseEntity.ok("Cliente de Restaurante cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Cliente de Restaurante");
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ClienteRestauranteRequestDto request) {
        try {
            if (clienteRestauranteService.buscarPorId(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente de Restaurante não encontrado");
            }
            clienteRestauranteService.salvar(converter.dtoParaEntity(id, request));
            return ResponseEntity.ok("Cliente de Restaurante atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Cliente de Restaurante");
        }
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<?> buscarTodos() {
        try {
            List<ClienteRestauranteEntity> clientes = clienteRestauranteService.buscarTodos();
            if (clientes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Nenhum Cliente de Restaurante encontrado");
            }
            List<ClienteRestauranteResponseDto> response = clientes.stream()
                    .sorted(Comparator.comparing(ClienteRestauranteEntity::getId))
                    .map(converter::entityParaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Clientes de Restaurante");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            ClienteRestauranteEntity cliente = clienteRestauranteService.buscarPorId(id);
            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cliente de Restaurante não encontrado");
            }
            ClienteRestauranteResponseDto response = converter.entityParaDto(cliente);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Cliente de Restaurante");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            clienteRestauranteService.deletar(id);
            return ResponseEntity.ok("Cliente de Restaurante deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar Cliente de Restaurante");
        }
    }

}

