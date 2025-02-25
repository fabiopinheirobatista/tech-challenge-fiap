package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.DeleteRestauranteUseCase;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRestauranteService implements DeleteRestauranteUseCase {

    private final RestauranteRepository repository;

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
