package br.com.techchallenge.infra.adpter.repository;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.RestauranteBuscarPorIdInterface;
import br.com.techchallenge.domain.gateway.RestauranteDeletarInterface;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RestauranteDeletarRepositoryImp implements RestauranteDeletarInterface {

    private final RestauranteRepository repository;
    private final RestauranteDTOConverter converter;

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
