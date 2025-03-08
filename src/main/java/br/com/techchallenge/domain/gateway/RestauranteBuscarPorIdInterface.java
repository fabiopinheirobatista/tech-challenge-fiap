package br.com.techchallenge.domain.gateway;

import br.com.techchallenge.domain.entity.Restaurante;

import java.util.List;
import java.util.Optional;


public interface RestauranteBuscarPorIdInterface {

    Optional<Restaurante> buscarPorId(Long id);

}
