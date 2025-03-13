package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import java.util.List;

public interface ItensCardapioBuscarTodosInterface {
    List<ItensCardapio> buscarTodos();
}