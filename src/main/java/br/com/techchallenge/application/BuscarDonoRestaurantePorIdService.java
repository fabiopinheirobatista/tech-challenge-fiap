package br.com.techchallenge.application;

import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.DonoRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarDonoRestaurantePorIdService implements BuscarDonoRestaurantePorIdUseCase{

    private final DonoRestauranteRepository repository;

    public BuscarDonoRestaurantePorIdService(DonoRestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DonoRestaurante> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
