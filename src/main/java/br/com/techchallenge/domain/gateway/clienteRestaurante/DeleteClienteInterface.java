package br.com.techchallenge.domain.gateway.clienteRestaurante;

import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;

public interface DeleteClienteInterface {

    Boolean delete(Long id) throws ClienteNaoEncontradoException;
}
