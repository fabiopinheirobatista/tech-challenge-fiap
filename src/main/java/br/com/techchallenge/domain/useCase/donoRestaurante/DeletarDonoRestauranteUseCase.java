package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.donoRestaurante.DeletarDonoRestauranteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class DeletarDonoRestauranteUseCase {

    private final DeletarDonoRestauranteInterface donoRestauranteInterface;

    public void deletar(Long id) {
        try {
            donoRestauranteInterface.deletar(id);
        } catch (Exception | DonoRestauranteNaoEncontradoException e) {
            throw new RuntimeException("Erro ao deletar Dono de Restaurante" + e.getMessage());
        }
    }

}
