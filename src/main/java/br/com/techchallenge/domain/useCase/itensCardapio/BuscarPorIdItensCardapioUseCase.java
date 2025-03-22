package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarPorIdInterface;

import java.util.Optional;

public class BuscarPorIdItensCardapioUseCase {

    private final ItensCardapioBuscarPorIdInterface repository;

    public BuscarPorIdItensCardapioUseCase(ItensCardapioBuscarPorIdInterface repository) {
        this.repository = repository;
    }

    public Optional<ItensCardapio> execute(Long id) {
        return repository.buscarPorId(id);
    }

}
