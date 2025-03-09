package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.RestauranteAtualizarInterface;
import br.com.techchallenge.domain.gateway.RestauranteBuscarPorIdInterface;

import java.util.Optional;

public class AtualizarRestauranteUseCase {

    private final RestauranteAtualizarInterface atualizarRepository;
    private final RestauranteBuscarPorIdInterface buscarPorIdRepository;

    public AtualizarRestauranteUseCase(RestauranteAtualizarInterface atualizarRepository, RestauranteBuscarPorIdInterface buscarPorIdRepository) {
        this.buscarPorIdRepository = buscarPorIdRepository;
        this.atualizarRepository = atualizarRepository;
    }

    public Restaurante execute(Long id, Restaurante restaurante) throws RestauranteNaoEncontradoException {
        Optional<Restaurante> restauranteOptional = buscarPorIdRepository.buscarPorId(id);

        if (restauranteOptional.isEmpty()) throw new RestauranteNaoEncontradoException("Restaurante não encontrado");

        return atualizarRepository.update(restaurante);
    }
}