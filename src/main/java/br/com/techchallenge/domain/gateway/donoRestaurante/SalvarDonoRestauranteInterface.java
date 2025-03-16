package br.com.techchallenge.domain.gateway.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import org.springframework.stereotype.Component;

@Component
public interface SalvarDonoRestauranteInterface {

    DonoRestaurante atualizar(DonoRestauranteEntity donoRestauranteEntity);
    DonoRestaurante salvar(DonoRestauranteEntity donoRestauranteEntity);
}
