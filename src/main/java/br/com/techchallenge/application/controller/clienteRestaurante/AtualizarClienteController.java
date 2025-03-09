package br.com.techchallenge.application.controller.clienteRestaurante;


import br.com.techchallenge.domain.dto.clienteRestaurante.ClienteRestauranteRequestDto;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.useCase.cliente.SalvarClienteUseCase;
import br.com.techchallenge.infra.converter.clienteRestaurante.ClienteRestauranteDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente-restaurante")
@RequiredArgsConstructor
public class AtualizarClienteController {

    private final SalvarClienteUseCase salvarClienteUseCase;
    private final ClienteRestauranteDtoConverter converter;

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody ClienteRestauranteRequestDto request) throws ClienteNaoEncontradoException {
        salvarClienteUseCase.atualizar(converter.dtoParaEntity(id, request));
        return ResponseEntity.ok("Cliente de Restaurante atualizado com sucesso");
    }
}
