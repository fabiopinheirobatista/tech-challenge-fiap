package br.com.techchallenge.application.controller.clienteRestaurante;


import br.com.techchallenge.domain.exception.ClienteJaCadastradoException;
import br.com.techchallenge.domain.input.clienteRestaurante.ClienteRestauranteRequestDto;
import br.com.techchallenge.domain.useCase.clienteRestaurante.SalvarClienteUseCase;
import br.com.techchallenge.infra.converter.clienteRestaurante.ClienteRestauranteDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente-restaurante")
@RequiredArgsConstructor
public class CadastrarClienteController {

    private final SalvarClienteUseCase salvarClienteUseCase;
    private final ClienteRestauranteDtoConverter converter;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ClienteRestauranteRequestDto request) throws ClienteJaCadastradoException {

        salvarClienteUseCase.cadastrar(converter.dtoParaEntity(request));
        return ResponseEntity.ok("Cliente de Restaurante cadastrado com sucesso");


    }
}
