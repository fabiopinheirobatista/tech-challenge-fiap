package br.com.techchallenge.infra.adapter.repository.itensCardapio;


import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioSalvarInterface;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItensCardapioCadastrarRepositoryImp implements ItensCardapioSalvarInterface {

    private final ItensCardapioRepository repositoryItensCardapio;
    private final RestauranteRepository restauranteRepository;
    private final ItensCardapioDTOConverter itensCardapioDTOConverter;

    @Override
    public ItensCardapio salvar(ItensCardapio item) {
        ItensCardapioEntity entity = itensCardapioDTOConverter.domainToEntity(item);
        ItensCardapioEntity save = repositoryItensCardapio.save(entity);
        return itensCardapioDTOConverter.entityToDomain(save);
    }

    @Override
    public Optional<RestauranteEntity> buscarPorId(Long idRestaurante) {
        return restauranteRepository.findById(idRestaurante);
    }
}
