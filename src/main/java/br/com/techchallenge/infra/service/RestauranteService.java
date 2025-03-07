package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    private final RestauranteRepository repository;
    private final DonoRestauranteRepository donoRestauranteRepository;

    public RestauranteService(RestauranteRepository repository, DonoRestauranteRepository donoRestauranteRepository) {
        this.repository = repository;
        this.donoRestauranteRepository = donoRestauranteRepository;
    }

    public Optional<RestauranteEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public boolean nomeRestauranteExiste(String nome) {
        return repository.existsByNome(nome);
    }

    public List<RestauranteEntity> buscarTodos() {
        return repository.findAll();
    }

    public void salvar(RestauranteEntity restaurante) {
        repository.save(restaurante);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
