package br.com.techchallenge.domain.gateway.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItensCardapioBuscarPorIdInterface {

    Optional<ItensCardapio> buscarPorId(Long id);
}