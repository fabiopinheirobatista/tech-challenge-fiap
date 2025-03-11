package br.com.techchallenge.infra.adpter.repository.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioDeletarInterface;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ItensCardapioDeletarRepositoryImp implements ItensCardapioDeletarInterface {

    private final ItensCardapioRepository repository;
    private final ItensCardapioDTOConverter itensCardapioDTOConverter;

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ItensCardapio> buscarPorId(Long id) {
        Optional<ItensCardapioEntity> buscarItemCardapio = repository.findById(id);

        return buscarItemCardapio
                .map(itensCardapioDTOConverter::entityToDomain);
    }
}
