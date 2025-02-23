package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.adapters.useCaseImpl.restaurante.RestauranteCadastrarUseCase;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestauranteServiceCadastrar implements RestauranteCadastrarUseCase {

    private final RestauranteRepository repository;

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {
        return repository.save(restaurante);
    }
}