package br.com.techchallenge.infra.adapter.repository.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.gateway.donoRestaurante.BuscarTodosDonoRestauranteInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarTodosDonoRestauranteImpl implements BuscarTodosDonoRestauranteInterface {
    @Override
    public List<DonoRestaurante> buscarTodos() {
        return List.of();
    }
}
