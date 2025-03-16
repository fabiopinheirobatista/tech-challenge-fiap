package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteJaCadastradoException;
import br.com.techchallenge.domain.exception.DonoRestauranteJaCadastradoException;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.donoRestaurante.SalvarDonoRestauranteInterface;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SalvarDonoRestauranteUseCase {

    private final SalvarDonoRestauranteInterface donoRestauranteInterface;
    private final DonoRestauranteRepository donoRestauranteRepository;

    public DonoRestaurante atualizar(DonoRestauranteEntity donoRestauranteEntity) throws DonoRestauranteNaoEncontradoException {
        DonoRestauranteEntity donoRestauranteExistente = donoRestauranteRepository.findById(donoRestauranteEntity.getId())
                .orElseThrow(() -> new DonoRestauranteNaoEncontradoException("Dono de Restaurante não encontrado"));

        donoRestauranteExistente.setNome(donoRestauranteEntity.getNome());
        donoRestauranteExistente.setEmail(donoRestauranteEntity.getEmail());
        donoRestauranteExistente.setLogin(donoRestauranteEntity.getLogin());
        donoRestauranteExistente.setSenha(donoRestauranteEntity.getSenha());

        return donoRestauranteInterface.atualizar(donoRestauranteExistente);
    }

    public DonoRestaurante cadastrar(DonoRestauranteEntity donoRestauranteEntity) throws DonoRestauranteJaCadastradoException {

        if(donoRestauranteRepository.existsByEmailOrLogin(donoRestauranteEntity.getEmail(), donoRestauranteEntity.getLogin())) {
            throw new DonoRestauranteJaCadastradoException("Dono de Restaurante já cadastrado com este e-mail/login.");
        }

        return donoRestauranteInterface.cadastrar(donoRestauranteEntity);
    }

}
