package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.infra.entity.RestauranteEntity;

import java.util.Optional;

public interface ItensCardapioSalvarInterface {
    ItensCardapio salvar(ItensCardapio item);
    Optional<RestauranteEntity> buscarPorId(Long idRestaurante);
}