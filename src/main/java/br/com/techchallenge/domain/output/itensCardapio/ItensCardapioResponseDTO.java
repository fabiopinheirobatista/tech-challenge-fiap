package br.com.techchallenge.domain.output.itensCardapio;

import br.com.techchallenge.domain.entity.Restaurante;

public record ItensCardapioResponseDTO(
        Long id,
        String nome,
        String descricao,
        double preco,
        String disponibilidade,
        String fotoPrato,
        Long idRestaurante
) {}