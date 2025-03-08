package br.com.techchallenge.helper;

import br.com.techchallenge.domain.entity.clienteRestaurante.ClienteRestaurante;

public abstract class ClienteRestauranteHelper {

    public static ClienteRestaurante clienteRestaurante(){
        return new ClienteRestaurante(1L, "João", "joao@teste.com", "joao", "123456");
    }
}
