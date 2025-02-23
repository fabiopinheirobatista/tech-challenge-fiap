package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuscarRestaurantePorIdService implements BuscarRestaurantePorIdUseCase {

    private final RestauranteRepository repository;

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
