package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.RestauranteBuscarPorIdInterface;

import java.util.Optional;

public class BuscarRestaurantePorIdUseCase {

    private final RestauranteBuscarPorIdInterface repository;

    public BuscarRestaurantePorIdUseCase(RestauranteBuscarPorIdInterface repository) {
        this.repository = repository;
    }

    public Optional<Restaurante> execute(Long id) {
        return repository.buscarPorId(id);
    }
}
