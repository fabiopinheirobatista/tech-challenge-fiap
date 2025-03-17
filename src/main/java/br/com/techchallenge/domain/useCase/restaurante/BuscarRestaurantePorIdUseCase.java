package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuscarRestaurantePorIdUseCase {

    private final RestauranteBuscarPorIdInterface repository;

    public BuscarRestaurantePorIdUseCase(RestauranteBuscarPorIdInterface repository) {
        this.repository = repository;
    }

    public Optional<Restaurante> execute(Long id) {
        return repository.buscarPorId(id);
    }
}
