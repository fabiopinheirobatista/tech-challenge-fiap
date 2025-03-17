package br.com.techchallenge.infra.adapter.repository.restaurante;

import br.com.techchallenge.domain.gateway.restaurante.RestauranteDeletarInterface;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestauranteDeletarRepositoryImp implements RestauranteDeletarInterface {

    private final RestauranteRepository repository;
    private final RestauranteDTOConverter converter;

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
