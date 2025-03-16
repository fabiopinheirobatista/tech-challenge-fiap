package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.donoRestaurante.BuscarPorIdDonoRestauranteInterface;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteListarIdResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class BuscarPorIdDonoRestauranteUseCase {

    private final BuscarPorIdDonoRestauranteInterface donoRestauranteInterface;

    public DonoRestauranteListarIdResponseDTO execute(Long id) {
        DonoRestauranteListarIdResponseDTO donoRestauranteDTO = null;
        try {
            DonoRestaurante donoRestaurante = donoRestauranteInterface.buscarPorId(id);
            donoRestauranteDTO = new DonoRestauranteListarIdResponseDTO(
                    donoRestaurante.getId(),
                    donoRestaurante.getNome(),
                    donoRestaurante.getEndereco().toString(),
                    donoRestaurante.getEmail(),
                    donoRestaurante.getLogin()
            );
        } catch (Exception | DonoRestauranteNaoEncontradoException e) {
            throw new RuntimeException("Erro ao buscar Dono de Restaurante pelo ID " + e.getMessage());
        }

        return donoRestauranteDTO;
    }

}
