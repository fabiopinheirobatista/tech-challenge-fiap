package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.gateway.RestauranteDeletarInterface;

public class ExcluirRestauranteUseCase {
    private final RestauranteDeletarInterface repository;

    public ExcluirRestauranteUseCase(RestauranteDeletarInterface repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.deletar(id);
    }
}