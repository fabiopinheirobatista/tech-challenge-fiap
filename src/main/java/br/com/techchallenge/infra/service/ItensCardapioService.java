package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItensCardapioService {

    private final ItensCardapioRepository repository;

    public ItensCardapioService(ItensCardapioRepository repository) {
        this.repository = repository;
    }

    public ItensCardapioEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public ItensCardapioEntity salvar(ItensCardapioEntity ItensCardapio) {
        return repository.save(ItensCardapio);
    }

    public List<ItensCardapioEntity> buscarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("ID informado não existe");
        }
        repository.deleteById(id);
    }
}
