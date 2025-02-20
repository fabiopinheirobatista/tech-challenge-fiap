package br.com.techchallenge.application;

import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.DonoRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastrarDonoRestauranteService implements CadastrarDonoRestauranteUseCase {

    private final DonoRestauranteRepository repository;

    public CadastrarDonoRestauranteService(DonoRestauranteRepository repository) {
        this.repository = repository;
    }

    @Override
    public DonoRestaurante cadastrar(DonoRestaurante dono) {
        return repository.save(dono);
    }
}