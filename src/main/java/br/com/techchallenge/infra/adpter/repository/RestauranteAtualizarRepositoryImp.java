package br.com.techchallenge.infra.adpter.repository;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.RestauranteAtualizarInterface;
import br.com.techchallenge.domain.gateway.RestauranteSalvarInterface;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteAtualizarRepositoryImp implements RestauranteAtualizarInterface {

    private final RestauranteRepository repository;
    private final RestauranteDTOConverter converter;

    @Override
    public Restaurante update(Restaurante restaurante) {
        RestauranteEntity restauranteEntity = converter.restauranteParaEntity(restaurante);
        RestauranteEntity restauranteSalvo = repository.save(restauranteEntity);
        return converter.restauranteEntityToRestaurante(restauranteSalvo);
    }
}
