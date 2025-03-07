package br.com.techchallenge.application.controller.clienteRestaurante;


import br.com.techchallenge.domain.dto.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.domain.useCase.cliente.BuscarClientePorIdUseCase;
import br.com.techchallenge.infra.adapter.repository.BuscarClienteRepository;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente-restaurante")
@RequiredArgsConstructor
public class BuscarClientePorIdController {

    private final ClienteRestauranteRepository clienteRestauranteRepository;

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            BuscarClientePorIdUseCase useCase = new BuscarClientePorIdUseCase(
                    new BuscarClienteRepository(clienteRestauranteRepository)
            );

            ClienteRestauranteResponseDto cliente = useCase.execute(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar Cliente de Restaurante");
        }
    }
}
