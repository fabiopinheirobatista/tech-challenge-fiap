package br.com.techchallenge.infra.adapter.repository.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.gateway.donoRestaurante.SalvarDonoRestauranteInterface;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("salvarDonoRestauranteInterface")
@RequiredArgsConstructor
public class SalvarDonoRestauranteRepositoryImpl implements SalvarDonoRestauranteInterface {

    private final DonoRestauranteRepository donoRestauranteRepository;
    private final DonoRestauranteDTOConverter donoRestauranteDTOConverter;

    @Override
    public DonoRestaurante atualizar(DonoRestauranteEntity donoRestauranteEntity) {
        DonoRestauranteEntity donoSalvo = donoRestauranteRepository.save(donoRestauranteEntity);
        return donoRestauranteDTOConverter.entityParaDonoRestaurante(donoSalvo);
    }

    @Override
    public DonoRestaurante salvar(DonoRestauranteEntity donoRestauranteEntity) {
        DonoRestauranteEntity donoSalvo = donoRestauranteRepository.save(donoRestauranteEntity);
        return donoRestauranteDTOConverter.entityParaDonoRestaurante(donoSalvo);
    }
}