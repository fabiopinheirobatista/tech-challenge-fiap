package br.com.techchallenge.application.controller.clienteRestaurante;


import br.com.techchallenge.domain.useCase.clienteRestaurante.DeleteClienteUseCase;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/cliente-restaurante")
@RequiredArgsConstructor
public class DeletarClienteController {

    private final ClienteRestauranteRepository clienteRestauranteRepository;
    private final DeleteClienteUseCase deleteClienteUseCase;

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        Optional<ClienteRestauranteEntity> cliente = clienteRestauranteRepository.findById(id);
        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente não excluído pois o registro não foi localizado.");
        }
        deleteClienteUseCase.execute(id);
        return ResponseEntity.ok("Cliente de Restaurante deletado com sucesso");
    }
}
