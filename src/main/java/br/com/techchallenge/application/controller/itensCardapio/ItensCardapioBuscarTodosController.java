package br.com.techchallenge.application.controller.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarTodosInterface;
import br.com.techchallenge.domain.output.itensCardapio.ItensCardapioResponseDTO;
import br.com.techchallenge.domain.useCase.itensCardapio.BuscarTodosItensCardapioUseCase;
import br.com.techchallenge.infra.adpter.repository.itensCardapio.ItensCardapioBuscarPorIdRepositoryImp;
import br.com.techchallenge.infra.adpter.repository.itensCardapio.ItensCardapioBuscarTodosRepositoryImp;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItensCardapioBuscarTodosController {

    private final ItensCardapioRepository itensCardapioRepository;
    private final ItensCardapioDTOConverter converter;

    public ItensCardapioBuscarTodosController(ItensCardapioRepository itensCardapioRepository, ItensCardapioDTOConverter converter) {
        this.itensCardapioRepository = itensCardapioRepository;
        this.converter = converter;
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<?> buscarTodos() {
        try {
            BuscarTodosItensCardapioUseCase useCase = new BuscarTodosItensCardapioUseCase(new ItensCardapioBuscarTodosRepositoryImp(itensCardapioRepository,converter));
            List<ItensCardapio> itens = useCase.execute();
            if (itens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum item cadastrado");
            }
            List<ItensCardapioResponseDTO> response = itens.stream()
                    .map(converter::domainToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar itens");
        }
    }
}