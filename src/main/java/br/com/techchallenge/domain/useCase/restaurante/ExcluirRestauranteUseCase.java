package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.RestauranteBuscarPorIdInterface;
import br.com.techchallenge.domain.gateway.RestauranteDeletarInterface;

import java.util.Optional;

public class ExcluirRestauranteUseCase {

    private final RestauranteDeletarInterface repositoryDeletar;
    private final RestauranteBuscarPorIdInterface repositoryPorId;

    public ExcluirRestauranteUseCase(RestauranteDeletarInterface repositoryDeletar, RestauranteBuscarPorIdInterface repositoryPorId) {
        this.repositoryDeletar = repositoryDeletar;
        this.repositoryPorId = repositoryPorId;
    }

    public void execute(Long id) throws RestauranteNaoEncontradoException {
        Optional<Restaurante> restauranteOptional = repositoryPorId.buscarPorId(id);

        if (restauranteOptional.isEmpty()) throw new RestauranteNaoEncontradoException("Restaurante não encontrado");
        repositoryDeletar.deletar(id);

    }
}