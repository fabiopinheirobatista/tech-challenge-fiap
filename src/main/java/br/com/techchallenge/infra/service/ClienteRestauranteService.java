package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteRestauranteService {

    private final ClienteRestauranteRepository repository;

    public ClienteRestauranteEntity salvar(ClienteRestauranteEntity clienteRestaurante) {
        return repository.save(clienteRestaurante);
    }

    public ClienteRestauranteEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public List<ClienteRestauranteEntity> buscarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("ID informado não existe");
        }
        repository.deleteById(id);
    }
}
