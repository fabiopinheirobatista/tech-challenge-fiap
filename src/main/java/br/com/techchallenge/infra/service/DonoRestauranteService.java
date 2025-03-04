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

    public boolean donoRestauranteExiste(String email, String login) {
        return repository.existsByEmailOrLogin(email, login);
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

    public boolean validarLogin(Long id, String login, String senha) {
        DonoRestauranteEntity dono = repository.findById(id).orElse(null);
        if (dono == null) {
            return false;
        }
        return dono.getLogin().equals(login) && dono.getSenha().equals(senha);
    }
}
