package br.com.techchallenge.application.controller.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.techchallenge.domain.input.itensCardapio.ItensCardapioRequestDTO;
import br.com.techchallenge.domain.useCase.itensCardapio.AtualizarItensCardapioUseCase;
import br.com.techchallenge.infra.adapter.repository.itensCardapio.ItensCardapioAtualizarRepositoryImp;
import br.com.techchallenge.infra.adapter.repository.itensCardapio.ItensCardapioBuscarPorIdRepositoryImp;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItensCardapioAtualizarController {

    private final ItensCardapioRepository itensCardapioRepository;
    private final ItensCardapioDTOConverter converter;

    public ItensCardapioAtualizarController(ItensCardapioRepository itensCardapioRepository, ItensCardapioDTOConverter converter) {
        this.itensCardapioRepository = itensCardapioRepository;
        this.converter = converter;
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ItensCardapioRequestDTO request) {
        ItensCardapio item = converter.dtoToDomain(request);
        item.setId(id);
        AtualizarItensCardapioUseCase useCase = new AtualizarItensCardapioUseCase(
                new ItensCardapioAtualizarRepositoryImp(itensCardapioRepository, converter),
                new ItensCardapioBuscarPorIdRepositoryImp(itensCardapioRepository, converter)
        );
        ItensCardapio itemAtualizado = useCase.execute(item);
        return ResponseEntity.ok(converter.domainToDto(itemAtualizado));
    }
}
