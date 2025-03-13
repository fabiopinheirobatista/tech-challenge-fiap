package br.com.techchallenge.domain.gateway.clienteRestaurante;

import br.com.techchallenge.domain.entity.ClienteRestaurante;

import java.util.List;

public interface BuscarTodosOsClientesInterface {

    List<ClienteRestaurante> buscarTodos();
}
