package br.com.techchallenge.infra.service.donoRestaurante;

import br.com.techchallenge.adapters.useCaseImpl.donoRestaurante.DonoRestauranteAtualizarUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.repository.donoRestaurante.DonoRestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DonoRestauranteServiceAtualizar implements DonoRestauranteAtualizarUseCase {

    private final DonoRestauranteRepository repository;

    @Override
    public DonoRestaurante atualizar(DonoRestaurante donoRestaurante) {
        return repository.save(donoRestaurante);
    }
}
