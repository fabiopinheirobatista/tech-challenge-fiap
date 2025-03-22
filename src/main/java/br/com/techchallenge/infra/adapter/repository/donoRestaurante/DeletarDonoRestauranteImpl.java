package br.com.techchallenge.infra.adapter.repository.donoRestaurante;

import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.donoRestaurante.DeletarDonoRestauranteInterface;
import org.springframework.stereotype.Component;

@Component
public class DeletarDonoRestauranteImpl implements DeletarDonoRestauranteInterface {
    @Override
    public Boolean deletar(Long id) throws DonoRestauranteNaoEncontradoException {
        return null;
    }
}
