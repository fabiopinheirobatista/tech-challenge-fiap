package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<DonoRestauranteEntity> buscarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("ID informado não existe");
        }
        repository.deleteById(id);
    }
}
