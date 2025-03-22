package br.com.techchallenge.infra.adapter.repository.donoRestaurante;

import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.donoRestaurante.DeletarDonoRestauranteInterface;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import org.springframework.stereotype.Component;

@Component
public class DeletarDonoRestauranteImpl implements DeletarDonoRestauranteInterface {

    private final DonoRestauranteRepository donoRestauranteRepository;

    public DeletarDonoRestauranteImpl(DonoRestauranteRepository donoRestauranteRepository) {
        this.donoRestauranteRepository = donoRestauranteRepository;
    }

    @Override
    public Boolean deletar(Long id) throws DonoRestauranteNaoEncontradoException {
            donoRestauranteRepository.findById(id)
                    .orElseThrow(() -> new DonoRestauranteNaoEncontradoException("Dono de Restaurante não encontrado"));
            donoRestauranteRepository.deleteById(id);
            return true;
    }
}
