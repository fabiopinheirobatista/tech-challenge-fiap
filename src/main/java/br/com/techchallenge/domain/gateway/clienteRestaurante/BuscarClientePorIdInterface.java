package br.com.techchallenge.domain.gateway.clienteRestaurante;

import br.com.techchallenge.domain.entity.clienteRestaurante.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;

public interface BuscarClientePorIdInterface {

    ClienteRestaurante buscarPorId(Long id) throws ClienteNaoEncontradoException;
}
