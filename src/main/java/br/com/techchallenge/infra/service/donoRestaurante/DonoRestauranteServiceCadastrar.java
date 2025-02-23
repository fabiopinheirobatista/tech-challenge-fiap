package br.com.techchallenge.infra.service.donoRestaurante;

import br.com.techchallenge.adapters.useCaseImpl.donoRestaurante.DonoRestauranteCadastrarUseCase;
import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.repository.donoRestaurante.DonoRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonoRestauranteServiceCadastrar implements DonoRestauranteCadastrarUseCase {

    private final DonoRestauranteRepository repository;

    @Override
    public DonoRestaurante cadastrar(DonoRestaurante dono) {
        return repository.save(dono);
    }
}