package br.com.techchallenge.application.controller.itensCardapio;


import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.techchallenge.domain.useCase.itensCardapio.BuscarPorIdItensCardapioUseCase;
import br.com.techchallenge.domain.useCase.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.techchallenge.infra.adpter.repository.itensCardapio.ItensCardapioBuscarPorIdRepositoryImp;
import br.com.techchallenge.infra.adpter.repository.restaurante.RestauranteBuscarPorIdRepositoryImp;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItensCardapioBuscarPorIdController {

    private final ItensCardapioRepository itensCardapioRepository;
    private final ItensCardapioDTOConverter converter;

    public ItensCardapioBuscarPorIdController(ItensCardapioRepository itensCardapioRepository, ItensCardapioDTOConverter converter) {
        this.itensCardapioRepository = itensCardapioRepository;
        this.converter = converter;
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {

            BuscarPorIdItensCardapioUseCase useCase = new BuscarPorIdItensCardapioUseCase(new ItensCardapioBuscarPorIdRepositoryImp(itensCardapioRepository,converter));
            Optional<ItensCardapio> item = useCase.execute(id);

            if (item.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item do Cardapio com o ID informado não foi encontrado");
            }
            return ResponseEntity.ok(converter.domainToDto(item.get()));

        } catch (ItemCardapioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar item");
        }
    }
}
