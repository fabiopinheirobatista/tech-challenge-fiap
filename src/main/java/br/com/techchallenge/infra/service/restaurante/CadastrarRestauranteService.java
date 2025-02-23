package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarRestauranteService implements br.com.techchallenge.adapters.useCaseImpl.restaurante.CadastrarRestauranteUseCase {

    private final RestauranteRepository repository;

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {
        return repository.save(restaurante);
    }
}