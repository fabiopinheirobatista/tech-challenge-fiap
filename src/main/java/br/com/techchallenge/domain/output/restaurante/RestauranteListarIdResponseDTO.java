package br.com.techchallenge.domain.output.restaurante;

public record RestauranteListarIdResponseDTO(
        String nome,
        String endereco,
        String tipoCozinha
) {
}
