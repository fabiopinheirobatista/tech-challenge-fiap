package br.com.techchallenge.domain.gateway.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;

import java.util.Optional;


public interface RestauranteSalvarInterface {

    Restaurante salvar(Restaurante restaurante);
    boolean buscarPorNome(String nome);
    Optional<DonoRestaurante> buscarPorIdDonoRestaurante(Long id);
}
