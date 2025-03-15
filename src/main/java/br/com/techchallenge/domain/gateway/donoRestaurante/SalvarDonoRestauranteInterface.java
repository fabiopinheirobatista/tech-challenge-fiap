package br.com.techchallenge.domain.gateway.donoRestaurante;

import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;

public interface SalvarDonoRestauranteInterface {

    DonoRestaurante salvar(DonoRestauranteEntity donoRestaurante);

}
