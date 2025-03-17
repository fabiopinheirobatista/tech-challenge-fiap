package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteAtualizarInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
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