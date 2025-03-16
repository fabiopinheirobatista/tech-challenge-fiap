package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarLoginDonoRestauranteUseCase {

    private final DonoRestauranteRepository repository;

    public ValidarLoginDonoRestauranteUseCase(DonoRestauranteRepository repository) {
        this.repository = repository;
    }

    public boolean validarLogin(Long id, String login, String senha) {
        DonoRestauranteEntity dono = repository.findById(id).orElse(null);
        if (dono == null) {
            return false;
        }
        return dono.getLogin().equals(login) && dono.getSenha().equals(senha);
    }

}
