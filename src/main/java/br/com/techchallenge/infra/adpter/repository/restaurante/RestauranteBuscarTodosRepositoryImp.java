package br.com.techchallenge.infra.adpter.repository.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarTodosInterface;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RestauranteBuscarTodosRepositoryImp implements RestauranteBuscarTodosInterface {

    private final RestauranteRepository repository;
    private final RestauranteDTOConverter converter;

    @Override
    public List<Restaurante> buscarTodos() {
        return repository.findAll().stream().map(converter::restauranteEntityToRestaurante).collect(Collectors.toList());

    }
}
