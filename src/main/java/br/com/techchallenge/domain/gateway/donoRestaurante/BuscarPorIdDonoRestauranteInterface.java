package br.com.techchallenge.domain.gateway.donoRestaurante;

import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;
import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;

public interface BuscarPorIdDonoRestauranteInterface {

    DonoRestaurante buscarPorId(Long id) throws DonoRestauranteNaoEncontradoException;

}
