package br.com.techchallenge.application.controller.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.output.itensCardapio.ItensCardapioResponseDTO;
import br.com.techchallenge.domain.useCase.itensCardapio.BuscarTodosItensCardapioUseCase;
import br.com.techchallenge.infra.adapter.repository.itensCardapio.ItensCardapioBuscarTodosRepositoryImp;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/itens-cardapio")
@RequiredArgsConstructor
public class ItensCardapioBuscarTodosController {

    private final ItensCardapioRepository itensCardapioRepository;
    private final BuscarTodosItensCardapioUseCase buscarTodosItensCardapioUseCase;
    private final ItensCardapioDTOConverter converter;

    @GetMapping("/listar-todos")
    public ResponseEntity<?> buscarTodos() {
        List<ItensCardapio> itens = buscarTodosItensCardapioUseCase.execute();
        if (itens.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum item cadastrado");
        }
        List<ItensCardapioResponseDTO> response = itens.stream()
                .map(converter::domainToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}