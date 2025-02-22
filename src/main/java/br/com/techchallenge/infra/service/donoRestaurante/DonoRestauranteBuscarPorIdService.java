package br.com.techchallenge.infra.service.donoRestaurante;

import br.com.techchallenge.adapters.useCaseImpl.BuscarDonoRestaurantePorIdUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.repository.donoRestaurante.DonoRestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonoRestauranteBuscarPorIdService implements BuscarDonoRestaurantePorIdUseCase {

    private final DonoRestauranteRepository repository;

    public DonoRestauranteBuscarPorIdService(DonoRestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DonoRestaurante> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
