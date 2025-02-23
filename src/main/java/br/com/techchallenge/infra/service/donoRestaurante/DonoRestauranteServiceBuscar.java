package br.com.techchallenge.infra.service.donoRestaurante;

import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.repository.donoRestaurante.DonoRestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DonoRestauranteServiceBuscar {

    private final DonoRestauranteRepository repository;

    @Override
    public Optional<DonoRestaurante> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
