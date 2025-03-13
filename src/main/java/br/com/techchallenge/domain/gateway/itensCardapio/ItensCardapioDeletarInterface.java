package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;

import java.util.Optional;

public interface ItensCardapioDeletarInterface {
    void deletar(Long id);
    Optional<ItensCardapio> buscarPorId(Long id);
}