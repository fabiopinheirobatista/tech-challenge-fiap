package br.com.techchallenge.application.controller.clienteRestaurante;


import br.com.techchallenge.domain.useCase.cliente.DeleteClienteUseCase;
import br.com.techchallenge.infra.adapter.repository.DeleteClienteRepositoryImpl;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente-restaurante")
@RequiredArgsConstructor
public class DeletarClienteController {

    private final ClienteRestauranteRepository clienteRestauranteRepository;

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            DeleteClienteUseCase useCase = new DeleteClienteUseCase(
                    new DeleteClienteRepositoryImpl(clienteRestauranteRepository)
            );
            useCase.execute(id);
            return ResponseEntity.ok("Cliente de Restaurante deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar Cliente de Restaurante");
        }
    }
}
