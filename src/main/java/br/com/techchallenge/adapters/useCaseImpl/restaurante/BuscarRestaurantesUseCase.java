package br.com.techchallenge.adapters.UseCaseImpl.restaurante;

import br.com.techchallenge.domain.Restaurante;

import java.util.List;

public interface BuscarRestaurantesUseCase {

    List<Restaurante> buscarTodos();
}
