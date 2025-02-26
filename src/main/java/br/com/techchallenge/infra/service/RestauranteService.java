package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import br.com.techchallenge.shared.exception.InternalServerErrorException;
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

    public List<RestauranteEntity> buscarTodos() {
        return repository.findAll();
    }

    public void salvar(RestauranteEntity restaurante, Long idDonoRestaurante) throws InternalServerErrorException {
        donoRestauranteRepository.findById(idDonoRestaurante)
        .orElseThrow(() -> new InternalServerErrorException("Dono do restaurante não encontrado"));
        repository.save(restaurante);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
