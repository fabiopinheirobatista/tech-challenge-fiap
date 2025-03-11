package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import java.util.Optional;

public interface ItensCardapioBuscarPorIdInterface {

    Optional<ItensCardapio> buscarPorId(Long id);
}