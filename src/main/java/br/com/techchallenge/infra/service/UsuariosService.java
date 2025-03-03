package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.UsuariosEntity;
import br.com.techchallenge.infra.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository repository;

    public UsuariosEntity salvar(UsuariosEntity usuario) {
        usuario.setDataUltimaAlteracao(LocalDateTime.now().toLocalDate());
        return repository.save(usuario);
    }

    public Optional<UsuariosEntity> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<UsuariosEntity> buscarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
