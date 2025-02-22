package br.com.techchallenge.application;

import br.com.techchallenge.domain.DonoRestaurante;
import java.util.Optional;

public interface BuscarDonoRestaurantePorIdUseCase {
    Optional<DonoRestaurante> buscarPorId(Long id);
}