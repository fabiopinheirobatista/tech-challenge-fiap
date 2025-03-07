package br.com.techchallenge.domain.gateway;

import br.com.techchallenge.domain.clienteRestaurante.ClienteRestaurante;

import java.util.List;

public interface BuscarClienteInterface {

    List<ClienteRestaurante> buscarTodos();
}
