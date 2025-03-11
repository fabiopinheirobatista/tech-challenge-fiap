package br.com.techchallenge.infra.adpter.repository.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteSalvarInterface;
import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RestauranteCadastrarRepositoryImp implements RestauranteSalvarInterface {

    private final RestauranteRepository repositoryRestaurante;
    private final DonoRestauranteRepository repositoryDonoRestaurante;
    private final RestauranteDTOConverter converterRestaurante;
    private final DonoRestauranteDTOConverter converterDonoRestaurante;

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        RestauranteEntity restauranteEntity = converterRestaurante.restauranteParaEntity(restaurante);
        RestauranteEntity restauranteSalvo = repositoryRestaurante.save(restauranteEntity);
        return converterRestaurante.restauranteEntityToRestaurante(restauranteSalvo);
    }

    @Override
    public boolean buscarPorNome(String nome) {
        return repositoryRestaurante.existsByNome(nome);
    }

    @Override
    public Optional<DonoRestaurante> buscarPorIdDonoRestaurante(Long id) {
        Optional<DonoRestauranteEntity> donoRestauranteEntity = repositoryDonoRestaurante.findById(id);
        if (donoRestauranteEntity.isPresent()) {
            DonoRestaurante donoRestaurante=converterDonoRestaurante.entityParaDonoRestaurante(donoRestauranteEntity.get());
            return Optional.of(donoRestaurante);
        } else {
            return Optional.empty();
        }
    }
}
