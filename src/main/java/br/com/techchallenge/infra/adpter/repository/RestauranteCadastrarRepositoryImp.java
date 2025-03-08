package br.com.techchallenge.infra.adpter.repository;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.RestauranteSalvarInterface;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteCadastrarRepositoryImp implements RestauranteSalvarInterface {

    private final RestauranteRepository repository;
    private final RestauranteDTOConverter converter;

    @Override
    public void salvar(Restaurante restaurante) {
        RestauranteEntity restauranteEntity = converter.restauranteParaEntity(restaurante);
        repository.save(restauranteEntity);
    }
}
