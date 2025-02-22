package br.com.techchallenge.infra.service.donoRestaurante;

import br.com.techchallenge.adapters.useCaseImpl.CadastrarDonoRestauranteUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.repository.donoRestaurante.DonoRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class DonoRestauranteCadastrarService implements CadastrarDonoRestauranteUseCase {

    private final DonoRestauranteRepository repository;

    public DonoRestauranteCadastrarService(DonoRestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public DonoRestaurante cadastrar(DonoRestaurante dono) {
        return repository.save(dono);
    }
}