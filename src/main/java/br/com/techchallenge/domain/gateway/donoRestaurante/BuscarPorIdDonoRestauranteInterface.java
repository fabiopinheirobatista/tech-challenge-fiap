package br.com.techchallenge.domain.gateway.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;

public interface BuscarPorIdDonoRestauranteInterface {

    DonoRestaurante buscarPorId(Long id) throws DonoRestauranteNaoEncontradoException;

}
