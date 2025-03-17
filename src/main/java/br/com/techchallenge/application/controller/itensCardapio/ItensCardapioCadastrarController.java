package br.com.techchallenge.application.controller.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.input.itensCardapio.ItensCardapioRequestDTO;
import br.com.techchallenge.domain.useCase.itensCardapio.CadastrarItensCardapioUseCase;
import br.com.techchallenge.infra.adapter.repository.itensCardapio.ItensCardapioCadastrarRepositoryImp;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itens-cardapio")
@RequiredArgsConstructor
public class ItensCardapioCadastrarController {

    private final ItensCardapioDTOConverter converter;
    private final CadastrarItensCardapioUseCase cadastrarUseCase;


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ItensCardapioRequestDTO request) throws RestauranteNaoEncontradoException {
        ItensCardapio item = cadastrarUseCase.execute(converter.dtoToDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.domainToDto(item));

    }
}