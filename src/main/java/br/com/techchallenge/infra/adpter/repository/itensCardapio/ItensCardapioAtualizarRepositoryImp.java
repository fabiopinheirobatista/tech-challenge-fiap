package br.com.techchallenge.infra.adpter.repository.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioAtualizarInterface;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItensCardapioAtualizarRepositoryImp implements ItensCardapioAtualizarInterface {

    private final ItensCardapioRepository repository;
    private final ItensCardapioDTOConverter itensCardapioDTOConverter;

    @Override
    public ItensCardapio update(ItensCardapio item) {
        ItensCardapioEntity entity = itensCardapioDTOConverter.domainToEntity(item);
        ItensCardapioEntity save = repository.save(entity);
        return itensCardapioDTOConverter.entityToDomain(save);
    }
}
