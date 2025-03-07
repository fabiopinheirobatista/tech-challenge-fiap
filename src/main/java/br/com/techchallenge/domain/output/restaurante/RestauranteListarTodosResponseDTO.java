package br.com.techchallenge.domain.output.restaurante;

public record RestauranteListarTodosResponseDTO(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha
) {
}
