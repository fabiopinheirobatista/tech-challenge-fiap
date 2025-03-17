package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarTodosInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarTodosInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarTodosItensCardapioUseCase {

    private final ItensCardapioBuscarTodosInterface repository;

    public BuscarTodosItensCardapioUseCase(ItensCardapioBuscarTodosInterface repository) {
        this.repository = repository;
    }

    public List<ItensCardapio> execute() {
        return repository.buscarTodos();
    }

}