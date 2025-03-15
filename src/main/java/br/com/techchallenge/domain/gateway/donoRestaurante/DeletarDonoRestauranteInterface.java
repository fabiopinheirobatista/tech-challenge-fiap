package br.com.techchallenge.domain.gateway.donoRestaurante;

import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;

public interface DeletarDonoRestauranteInterface {

    Boolean deletar(Long id) throws DonoRestauranteNaoEncontradoException;

}
