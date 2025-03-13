package br.com.techchallenge.domain.gateway.clienteRestaurante;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import org.springframework.stereotype.Component;

@Component
public interface SalvarClienteInterface {

    ClienteRestaurante atualizar(ClienteRestauranteEntity clienteRestauranteEntity) throws ClienteNaoEncontradoException;

    ClienteRestaurante cadastrar(ClienteRestauranteEntity clienteRestauranteEntity);
}
