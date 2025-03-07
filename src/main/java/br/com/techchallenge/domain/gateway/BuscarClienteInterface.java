package br.com.techchallenge.domain.gateway;

import br.com.techchallenge.domain.clienteRestaurante.ClienteRestaurante;

import java.util.List;

public interface BuscarClienteInterface {

    ClienteRestaurante buscarPorId(Long id);

    List<ClienteRestaurante> buscarTodos();
}
