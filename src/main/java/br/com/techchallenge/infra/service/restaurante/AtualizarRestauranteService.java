package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.AtualizarRestauranteUseCase;
import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtualizarRestauranteService implements AtualizarRestauranteUseCase {

    private final RestauranteRepository repository;
    private final RestauranteMapper mapper;

    @Override
    public Restaurante atualizar(Restaurante restaurante) {

        Optional<RestauranteEntity> restauranteOptional = repository.findById(restaurante.getId());

        if (restauranteOptional.isPresent()) {
            RestauranteEntity restauranteExistente = restauranteOptional.get();

            restauranteExistente.setNome(restaurante.getNome());
            restauranteExistente.setEndereco(restaurante.getEndereco());
            restauranteExistente.setTipoCozinha(restaurante.getTipoCozinha());

            var restauranteSalvo = repository.save(restauranteExistente);

            return mapper.toRestaurante(restauranteSalvo);
        } else {
            throw new EntityNotFoundException("Restaurante não encontrado");
        }

    }
}
