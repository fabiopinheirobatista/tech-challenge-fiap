package br.com.techchallenge.application.controller.itensCardapio;

import br.com.techchallenge.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.techchallenge.domain.useCase.itensCardapio.ExcluirItensCardapioUseCase;
import br.com.techchallenge.infra.adapter.repository.itensCardapio.ItensCardapioDeletarRepositoryImp;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItensCardapioDeletarController {

    private final ItensCardapioRepository itensCardapioRepository;
    private final ItensCardapioDTOConverter converter;

    public ItensCardapioDeletarController(ItensCardapioRepository itensCardapioRepository, ItensCardapioDTOConverter converter) {
        this.itensCardapioRepository = itensCardapioRepository;
        this.converter = converter;
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            ExcluirItensCardapioUseCase excluirItensCardapioUseCase = new ExcluirItensCardapioUseCase(
                    new ItensCardapioDeletarRepositoryImp(itensCardapioRepository, converter));

            excluirItensCardapioUseCase.execute(id);
            return ResponseEntity.ok("Item deletado com sucesso");
        } catch (ItemCardapioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar item");
        }
    }
}