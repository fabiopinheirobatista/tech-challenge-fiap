package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class DonoRestauranteService {

    private final DonoRestauranteRepository repository;

    public DonoRestauranteService(DonoRestauranteRepository repository) {
        this.repository = repository;
    }

    public DonoRestauranteEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public DonoRestauranteEntity salvar(DonoRestauranteEntity donoRestaurante) {
        return repository.save(donoRestaurante);
    }

}
