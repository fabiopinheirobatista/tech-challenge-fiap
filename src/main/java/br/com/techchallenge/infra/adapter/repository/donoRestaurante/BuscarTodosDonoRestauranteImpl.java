package br.com.techchallenge.infra.adapter.repository.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.gateway.donoRestaurante.BuscarTodosDonoRestauranteInterface;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarTodosDonoRestauranteImpl implements BuscarTodosDonoRestauranteInterface {

    private final DonoRestauranteRepository donoRestauranteRepository;

    @Override
    public List<DonoRestaurante> buscarTodos() {

        List<DonoRestauranteEntity> donoRestauranteEntity = donoRestauranteRepository.findAll();
        List<DonoRestaurante> donoRestaurantes = new ArrayList<>();


        for (DonoRestauranteEntity dono : donoRestauranteEntity) {
            Endereco endereco = new Endereco(dono.getEndereco());
            DonoRestaurante donoRestaurante = new DonoRestaurante(
                    dono.getId(),
                    dono.getNome(),
                    endereco,
                    dono.getEmail(),
                    dono.getLogin(),
                    dono.getSenha(),
                    dono.getDataUltimaAlteracao()
            );
            donoRestaurantes.add(donoRestaurante);
        }

        return donoRestaurantes;
    }
}
