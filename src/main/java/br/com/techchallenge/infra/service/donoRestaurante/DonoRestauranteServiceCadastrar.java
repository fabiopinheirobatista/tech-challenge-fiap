package br.com.techchallenge.infra.service.donoRestaurante;

import br.com.techchallenge.adapters.useCaseImpl.donoRestaurante.DonoRestauranteCadastrarUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.repository.donoRestaurante.DonoRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class DonoRestauranteServiceCadastrar implements DonoRestauranteCadastrarUseCase {

    private final DonoRestauranteRepository repository;

    public DonoRestauranteServiceCadastrar(DonoRestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public DonoRestaurante cadastrar(DonoRestaurante dono) {
        return repository.save(dono);
    }
}