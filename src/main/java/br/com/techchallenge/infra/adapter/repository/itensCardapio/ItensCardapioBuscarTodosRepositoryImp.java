package br.com.techchallenge.infra.adapter.repository.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;


import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioBuscarTodosInterface;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ItensCardapioBuscarTodosRepositoryImp implements ItensCardapioBuscarTodosInterface {

    private final ItensCardapioRepository repository;
    private final ItensCardapioDTOConverter converter;

    @Override
    public List<ItensCardapio> buscarTodos() {
        List<ItensCardapioEntity> buscarTodos = repository.findAll();
        return buscarTodos.stream()
                .map(converter::entityToDomain)
                .collect(Collectors.toList());
    }
}
