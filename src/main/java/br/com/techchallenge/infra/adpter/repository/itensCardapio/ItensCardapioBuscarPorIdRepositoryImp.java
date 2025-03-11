package br.com.techchallenge.infra.adpter.repository.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@RequiredArgsConstructor
public class ItensCardapioBuscarPorIdRepositoryImp implements br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarPorIdInterface{

    private final ItensCardapioRepository repository;
    private final ItensCardapioDTOConverter converter;

    @Override
    public Optional<ItensCardapio> buscarPorId(Long id) {
        Optional<ItensCardapioEntity> buscarItemCardapio = repository.findById(id);
        if (buscarItemCardapio.isEmpty()){

        }

        return buscarItemCardapio
                .map(converter::entityToDomain);
    }
}
