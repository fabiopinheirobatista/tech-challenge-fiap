package br.com.techchallenge.adapters.dto.itensCardapio;

import br.com.techchallenge.domain.endereco.Endereco;

public record ItensCardapioRequestDTO(
    Long id,
    String nome,
    Endereco descricao
) {}
