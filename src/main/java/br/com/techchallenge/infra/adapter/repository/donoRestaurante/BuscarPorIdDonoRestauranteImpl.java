package br.com.techchallenge.infra.adapter.repository.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.donoRestaurante.BuscarPorIdDonoRestauranteInterface;
import org.springframework.stereotype.Component;


@Component
public class BuscarPorIdDonoRestauranteImpl implements BuscarPorIdDonoRestauranteInterface {
    @Override
    public DonoRestaurante buscarPorId(Long id) throws DonoRestauranteNaoEncontradoException {
        return null;
    }
}
