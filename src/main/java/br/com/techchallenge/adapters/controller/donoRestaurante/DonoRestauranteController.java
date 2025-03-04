package br.com.techchallenge.adapters.controller.donoRestaurante;

import br.com.techchallenge.adapters.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.adapters.dto.donoRestaurante.*;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        if (service.donoRestauranteExiste(request.getEmail(), request.getLogin())) {
            return new ResponseEntity<>("Dono de Restaurante já cadastrado com esse e-mail/login", HttpStatus.CONFLICT);
        }
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

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Dono de Restaurante deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro ao deletar Dono de Restaurante");
        }
    }

    @PutMapping("/alterar-senha")
    public ResponseEntity<String> alterarSenha(@RequestBody DonoRestauranteAlterarSenhaRequestDTO request) {
        boolean atualizado = service.alterarSenha(request.getId(), request.getEmail(), request.getSenhaAtual(), request.getNovaSenha());
        if (!atualizado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atualização não realizada pois o ID informado não foi localizado ou o email/senha estão incorretos!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
    }

    @PostMapping("/validar-login")
    public ResponseEntity<String> validarLogin(@RequestBody DonoRestauranteValidarLoginRequestDTO request) {
        DonoRestauranteEntity dono = service.buscarPorId(request.getId());
        if (dono == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário inexistente!");
        }

        boolean isValid = service.validarLogin(request.getId(), request.getLogin(), request.getSenha());
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário/senha inválidos!");
        }

        return ResponseEntity.ok("Usuário validado com sucesso!");
    }

}
