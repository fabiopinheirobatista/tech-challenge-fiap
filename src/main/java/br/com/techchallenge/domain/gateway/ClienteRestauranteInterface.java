package br.com.techchallenge.domain.gateway;

import br.com.techchallenge.domain.entity.clienteRestaurante.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;

import java.util.List;

public interface ClienteRestauranteInterface {

    Boolean delete(Long id) throws ClienteNaoEncontradoException;

    ClienteRestaurante buscarPorId(Long id) throws ClienteNaoEncontradoException;

    List<ClienteRestaurante> buscarTodos();
}
