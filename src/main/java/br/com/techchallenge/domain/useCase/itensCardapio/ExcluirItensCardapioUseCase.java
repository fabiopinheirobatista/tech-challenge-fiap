package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.ItemCardapioNaoEncontradoException;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarPorIdInterface;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioDeletarInterface;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioSalvarInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteDeletarInterface;

import java.util.Optional;

public class ExcluirItensCardapioUseCase {


    private final ItensCardapioDeletarInterface repositoryDeletar;

    public ExcluirItensCardapioUseCase(ItensCardapioDeletarInterface repositoryDeletar) {
        this.repositoryDeletar = repositoryDeletar;
    }

    public void execute(Long id) {

        Optional<ItensCardapio> itensCardapio = repositoryDeletar.buscarPorId(id);

        if (itensCardapio.isEmpty()) throw new ItemCardapioNaoEncontradoException("Item Cardapio não encontrado");
        repositoryDeletar.deletar(id);

    }

}