package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarTodosInterface;

import java.util.List;

public class BuscarTodosRestauranteUseCase {

    private final RestauranteBuscarTodosInterface repository;

    public BuscarTodosRestauranteUseCase(RestauranteBuscarTodosInterface repository) {
        this.repository = repository;
    }

    public List<Restaurante> execute() {
        return repository. buscarTodos();
    }
}
