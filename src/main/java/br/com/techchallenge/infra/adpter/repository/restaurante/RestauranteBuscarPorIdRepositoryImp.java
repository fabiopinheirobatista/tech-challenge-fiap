package br.com.techchallenge.infra.adpter.repository.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RestauranteBuscarPorIdRepositoryImp implements RestauranteBuscarPorIdInterface {

    private final RestauranteRepository repository;
    private final RestauranteDTOConverter converter;

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        Optional<RestauranteEntity> restauranteEntity = repository.findById(id);
        if (restauranteEntity.isEmpty()) {
            return Optional.empty();
        }
        return restauranteEntity.map(converter::restauranteEntityToRestaurante);
    }
}
