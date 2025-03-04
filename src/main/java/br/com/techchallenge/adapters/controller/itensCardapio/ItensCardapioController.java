package br.com.techchallenge.adapters.controller.itensCardapio;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techchallenge.adapters.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.adapters.dto.itensCardapio.ItensCardapioListarIdResponseDTO;
import br.com.techchallenge.adapters.dto.itensCardapio.ItensCardapioListarTodosResponseDTO;
import br.com.techchallenge.adapters.dto.itensCardapio.ItensCardapioRequestDTO;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.service.ItensCardapioService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItensCardapioController {

    private final ItensCardapioDTOConverter converter;
    private final ItensCardapioService service;

    public ItensCardapioController(ItensCardapioDTOConverter converter, ItensCardapioService service) {
        this.converter = converter;
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ItensCardapioRequestDTO request) {
        try {
            service.salvar(converter.dtoParaEntity(request));
            return new ResponseEntity<>("Item do cardapio cadastrado com sucesso", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Item do cardapio já cadastrado com essas informações", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ItensCardapioRequestDTO request) {
        try {
            if (service.buscarPorId(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item do cardapio não encontrado");
            }
            service.salvar(converter.dtoParaEntity(id, request));
            return ResponseEntity.ok("Item do cardapio atualizado com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item do cardapio não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Item do cardapio");
        }
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<?> buscarTodos() {
        try {
            List<ItensCardapioEntity> itens = service.buscarTodos();
            if (itens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Não existem Items do cardapio cadastrados.");
            }
            List<ItensCardapioListarTodosResponseDTO> response = itens.stream()
                    .sorted(Comparator.comparing(ItensCardapioEntity::getId))
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
            ItensCardapioEntity item = service.buscarPorId(id);
            ItensCardapioListarIdResponseDTO dto = converter.entityParaListarIdDto(item);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item do cardapio não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item do cardapio não encontrado");
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Item do cardapio deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro ao deletar Item do cardapio");
        }
    }

}
