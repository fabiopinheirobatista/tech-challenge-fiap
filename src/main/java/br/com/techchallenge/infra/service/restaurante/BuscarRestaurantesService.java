package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.adapters.UseCaseImpl.restaurante.BuscarRestaurantesUseCase;
import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarRestaurantesService implements BuscarRestaurantesUseCase {

    private final RestauranteRepository repository;
    private final RestauranteMapper mapper;

    @Override
    public List<Restaurante> buscarTodos() {
        List<RestauranteEntity> entities = repository.findAll();
        return mapper.toRestauranteList(entities);
    }
}
