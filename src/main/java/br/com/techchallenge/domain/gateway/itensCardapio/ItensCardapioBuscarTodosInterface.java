package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensCardapioBuscarTodosInterface {
    List<ItensCardapio> buscarTodos();
}