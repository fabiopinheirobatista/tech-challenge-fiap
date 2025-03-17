package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioAtualizarInterface;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarPorIdInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteAtualizarInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AtualizarItensCardapioUseCase {

    private final ItensCardapioAtualizarInterface atualizarRepository;
    private final ItensCardapioBuscarPorIdInterface buscarPorIdRepository;

    public AtualizarItensCardapioUseCase(ItensCardapioAtualizarInterface atualizarRepository, ItensCardapioBuscarPorIdInterface buscarPorIdRepository) {
        this.atualizarRepository = atualizarRepository;
        this.buscarPorIdRepository = buscarPorIdRepository;
    }
    public ItensCardapio execute(ItensCardapio item) {
        Optional<ItensCardapio> itensCardapio = buscarPorIdRepository.buscarPorId(item.getId());
        if (itensCardapio.isEmpty()) throw new ItemCardapioNaoEncontradoException("Item não encontrado");
        return atualizarRepository.update(item);
    }
}