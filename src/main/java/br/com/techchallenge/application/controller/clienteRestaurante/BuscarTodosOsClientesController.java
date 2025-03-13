package br.com.techchallenge.application.controller.clienteRestaurante;


import br.com.techchallenge.domain.output.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.domain.useCase.clienteRestaurante.BuscarTodosOsClientesUseCase;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;
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
    private final BuscarTodosOsClientesUseCase buscarTodosOsClientesUseCase;

    @GetMapping("/buscar-todos")
    public ResponseEntity<?> buscarTodos() {
        List<ClienteRestauranteResponseDto> clientes = buscarTodosOsClientesUseCase.execute();
        return ResponseEntity.ok(clientes);
    }
}
