package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.RestauranteDeletarInterface;
import br.com.techchallenge.domain.gateway.RestauranteSalvarInterface;

public class AtualizarRestauranteUseCase {

    private final RestauranteSalvarInterface repository;

    public AtualizarRestauranteUseCase(RestauranteSalvarInterface repository) {
        this.repository = repository;
    }

    public void execute(Restaurante restaurante) {
        repository.salvar(restaurante);
    }
}