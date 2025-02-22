package br.com.techchallenge.adapters.useCaseImpl;

import br.com.techchallenge.domain.DonoRestaurante;
import java.util.Optional;

public interface BuscarDonoRestaurantePorIdUseCase {
    Optional<DonoRestaurante> buscarPorId(Long id);
}