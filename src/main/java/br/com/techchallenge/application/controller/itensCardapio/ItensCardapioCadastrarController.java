package br.com.techchallenge.application.controller.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.input.itensCardapio.ItensCardapioRequestDTO;
import br.com.techchallenge.domain.useCase.itensCardapio.CadastrarItensCardapioUseCase;
import br.com.techchallenge.infra.adapter.repository.itensCardapio.ItensCardapioCadastrarRepositoryImp;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itens-cardapio")
//@RequiredArgsConstructor
public class ItensCardapioCadastrarController {

    private final ItensCardapioRepository itensCardapioRepository;
    private final RestauranteRepository restauranteRepository;
    private final ItensCardapioDTOConverter converter;

    public ItensCardapioCadastrarController(ItensCardapioRepository itensCardapioRepository, RestauranteRepository restauranteRepository, ItensCardapioDTOConverter converter) {
        this.itensCardapioRepository = itensCardapioRepository;
        this.restauranteRepository = restauranteRepository;
        this.converter = converter;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ItensCardapioRequestDTO request) {
        try {

            CadastrarItensCardapioUseCase cadastrarUseCase = new CadastrarItensCardapioUseCase(
                    new ItensCardapioCadastrarRepositoryImp(itensCardapioRepository,restauranteRepository,converter));
            ItensCardapio item = converter.dtoToDomain(request);
            ItensCardapio itemSalvo = cadastrarUseCase.execute(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(converter.domainToDto(itemSalvo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar item");
        } catch (RestauranteNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Id do Restaurante não localizado!");
        }
    }
}