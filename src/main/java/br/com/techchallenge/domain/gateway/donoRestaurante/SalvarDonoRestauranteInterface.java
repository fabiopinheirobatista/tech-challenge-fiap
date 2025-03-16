package br.com.techchallenge.domain.gateway.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;

public interface SalvarDonoRestauranteInterface {

    DonoRestaurante atualizar(DonoRestauranteEntity donoRestaurante) throws DonoRestauranteNaoEncontradoException;

    DonoRestaurante cadastrar(DonoRestauranteEntity donoRestaurante);

}
