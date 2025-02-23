package br.com.techchallenge.adapters.controller.donoRestaurante;

import br.com.techchallenge.adapters.useCaseImpl.donoRestaurante.DonoRestauranteAtualizarUseCase;
import br.com.techchallenge.adapters.useCaseImpl.donoRestaurante.DonoRestauranteBuscarPorIdUseCase;
import br.com.techchallenge.adapters.useCaseImpl.donoRestaurante.DonoRestauranteCadastrarUseCase;
import br.com.techchallenge.application.mapper.DonoRestauranteMapper;
import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.dto.donoRestaurante.request.DonoRestauranteRequestDto;
import br.com.techchallenge.infra.dto.donoRestaurante.request.DonoRestauranteUpdateRequestDto;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/donos-restaurante")
@RequiredArgsConstructor
public class DonoRestauranteController {

    private final DonoRestauranteCadastrarUseCase donoRestauranteCadastrarUseCase;
    private final DonoRestauranteAtualizarUseCase donoRestauranteAtualizarUseCase;
    private final DonoRestauranteBuscarPorIdUseCase donoRestauranteBuscarPorIdUseCase;
    private final DonoRestauranteMapper mapper;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody DonoRestauranteRequestDto request) {
        try {
            DonoRestaurante dono = new DonoRestaurante(
                    request.nome(),
                    request.endereco(),
                    request.email(),
                    request.login(),
                    request.senha(),
                    LocalDate.now()
            );

            DonoRestauranteEntity entity = mapper.toDonoRestauranteEntity(dono);

            DonoRestaurante donoSalvo = donoRestauranteCadastrarUseCase.cadastrar(dono);

            return new ResponseEntity<>("Dono de Restaurante cadastrado com sucesso", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Dono de Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody DonoRestauranteUpdateRequestDto request) {
        Optional<DonoRestaurante> donoOptional = donoRestauranteBuscarPorIdUseCase.buscarPorId(id);

        if (donoOptional.isEmpty()) {
            return new ResponseEntity<>("Dono de Restaurante com o ID " + id + " não foi localizado", HttpStatus.NOT_FOUND);
        }

        DonoRestaurante existingDono = donoOptional.get();

        DonoRestaurante updatedDono = mapper.toDonoRestaurante(request);

        existingDono.setNome(updatedDono.nome());
        existingDono.setEndereco(updatedDono.endereco());
        existingDono.setEmail(updatedDono.email());
        existingDono.setLogin(updatedDono.login());

        donoRestauranteAtualizarUseCase.atualizar(existingDono);

        return new ResponseEntity<>("Dono de Restaurante com o ID " + id + " foi alterado com sucesso", HttpStatus.OK);
    }
}