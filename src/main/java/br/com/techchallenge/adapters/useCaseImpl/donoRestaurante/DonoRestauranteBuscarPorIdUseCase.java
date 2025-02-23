package br.com.techchallenge.adapters.useCaseImpl.donoRestaurante;

import br.com.techchallenge.domain.DonoRestaurante;

import java.util.Optional;

public interface DonoRestauranteBuscarPorIdUseCase {
    Optional<DonoRestaurante> buscarPorId(Long id);
}
