package br.com.techchallenge.domain.gateway.restaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.entity.Restaurante;

import java.util.Optional;

public interface RestauranteSalvarInterface {

    Restaurante salvar(Restaurante restaurante);
    boolean buscarPorNome(String nome);
    Optional<DonoRestaurante> buscarPorIdDonoRestaurante(Long id);
}
