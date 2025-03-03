package br.com.techchallenge.adapters.controller.usuarios;

import br.com.techchallenge.infra.entity.UsuariosEntity;
import br.com.techchallenge.infra.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuariosEntity> cadastrar(@RequestBody UsuariosEntity usuario) {
        UsuariosEntity novoUsuario = service.salvar(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<UsuariosEntity> buscarPorId(@PathVariable Long id) {
        Optional<UsuariosEntity> usuario = service.buscarPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<UsuariosEntity>> buscarTodos() {
        List<UsuariosEntity> usuarios = service.buscarTodos();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        service.deletar(id);
        return new ResponseEntity<>("Usuário deletado com sucesso", HttpStatus.OK);
    }
}
