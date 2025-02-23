package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.AtualizarRestauranteUseCase;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtualizarRestauranteService implements AtualizarRestauranteUseCase {

    private final RestauranteRepository repository;

    @Override
    public Restaurante atualizar(Restaurante restaurante) {

        Optional<Restaurante> restauranteOptional = repository.findById(restaurante.getId());

        if (restauranteOptional.isPresent()) {
            Restaurante restauranteExistente = restauranteOptional.get();

            restauranteExistente.setNome(restaurante.getNome());
            restauranteExistente.setEndereco(restaurante.getEndereco());
            restauranteExistente.setTipoCozinha(restaurante.getTipoCozinha());

            return repository.save(restauranteExistente);
        } else {
            throw new EntityNotFoundException("Restaurante não encontrado");
        }

    }
}
