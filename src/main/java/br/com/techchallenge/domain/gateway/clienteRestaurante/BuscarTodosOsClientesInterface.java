package br.com.techchallenge.domain.gateway.clienteRestaurante;

import br.com.techchallenge.domain.entity.clienteRestaurante.ClienteRestaurante;

import java.util.List;

public interface BuscarTodosOsClientesInterface {

    List<ClienteRestaurante> buscarTodos();
}
