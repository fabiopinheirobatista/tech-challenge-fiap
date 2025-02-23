package br.com.techchallenge.adapters.UseCaseImpl.restaurante;

import br.com.techchallenge.domain.Restaurante;

import java.util.Optional;

public interface BuscarRestaurantePorIdUseCase {

    Optional<Restaurante> buscarPorId(Long id);
}
