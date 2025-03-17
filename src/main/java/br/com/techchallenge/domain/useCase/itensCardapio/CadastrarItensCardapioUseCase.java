package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioSalvarInterface;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CadastrarItensCardapioUseCase {

    private final ItensCardapioSalvarInterface repositorySalvar;


    public CadastrarItensCardapioUseCase(ItensCardapioSalvarInterface repositorySalvar) {
        this.repositorySalvar = repositorySalvar;
    }

    public ItensCardapio execute(ItensCardapio restaurante) throws RestauranteNaoEncontradoException {
        Optional<RestauranteEntity> restauranteEntity = repositorySalvar.buscarPorId(restaurante.getIdRestaurante());
        if (restauranteEntity.isEmpty()) {
            throw new RestauranteNaoEncontradoException("Id do Restaurante não encontrado");
        }


        return repositorySalvar.salvar(restaurante);
    }
}