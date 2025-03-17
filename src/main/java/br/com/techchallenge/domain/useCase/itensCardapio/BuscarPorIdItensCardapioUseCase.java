package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarPorIdInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class BuscarPorIdItensCardapioUseCase {

    private final ItensCardapioBuscarPorIdInterface repository;

    public BuscarPorIdItensCardapioUseCase(ItensCardapioBuscarPorIdInterface repository) {
        this.repository = repository;
    }

    public Optional<ItensCardapio> execute(Long id) {
        return repository.buscarPorId(id);
    }

}
