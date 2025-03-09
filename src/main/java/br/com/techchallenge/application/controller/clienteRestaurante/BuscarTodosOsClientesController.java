package br.com.techchallenge.application.controller.clienteRestaurante;


import br.com.techchallenge.domain.dto.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.domain.useCase.cliente.BuscarTodosOsClientesUseCase;
import br.com.techchallenge.infra.adapter.repository.BuscarTodosOsClientesRepositoryImpl;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cliente-restaurante")
@RequiredArgsConstructor
public class BuscarTodosOsClientesController {

    private final ClienteRestauranteRepository clienteRestauranteRepository;

    @GetMapping("/buscar-todos")
    public ResponseEntity<?> buscarTodos() {
        try {
            BuscarTodosOsClientesUseCase useCase = new BuscarTodosOsClientesUseCase(
                    new BuscarTodosOsClientesRepositoryImpl(clienteRestauranteRepository)
            );
            List<ClienteRestauranteResponseDto> clientes = useCase.execute();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Clientes de Restaurante");
        }
    }
}
