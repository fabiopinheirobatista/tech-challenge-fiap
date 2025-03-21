package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import org.springframework.stereotype.Component;

@Component
public class AlterarSenhaDonoRestauranteUseCase {

    private final DonoRestauranteRepository repository;

    public AlterarSenhaDonoRestauranteUseCase(DonoRestauranteRepository repository) {
        this.repository = repository;
    }

    public boolean alterarSenha(Long id, String email, String senhaAtual, String novaSenha) {
        DonoRestauranteEntity dono = repository.findById(id).orElse(null);
        if (dono == null) {
            return false;
        }
        if (!dono.getEmail().equals(email) || !dono.getSenha().equals(senhaAtual)) {
            return false;
        }
        dono.setSenha(novaSenha);
        repository.save(dono);
        return true;
    }
}