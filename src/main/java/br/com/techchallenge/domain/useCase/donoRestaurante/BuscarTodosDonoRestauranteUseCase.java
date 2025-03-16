package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.domain.gateway.donoRestaurante.BuscarTodosDonoRestauranteInterface;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteListarTodosResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class BuscarTodosDonoRestauranteUseCase {

    private final BuscarTodosDonoRestauranteInterface buscarDonoRestauranteInterface;

    public List<DonoRestauranteListarTodosResponseDTO> execute() {
        List<DonoRestauranteListarTodosResponseDTO> listaDonoRestauranteDTO = new ArrayList<>();
        try {
            List<DonoRestaurante> listaDonoRestaurante = buscarDonoRestauranteInterface.buscarTodos();

            for (DonoRestaurante donoRestaurante : listaDonoRestaurante) {
                listaDonoRestauranteDTO.add(new DonoRestauranteListarTodosResponseDTO(
                        donoRestaurante.getId(),
                        donoRestaurante.getNome(),
                        donoRestaurante.getEndereco().toString(),
                        donoRestaurante.getEmail(),
                        donoRestaurante.getLogin()
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar Donos de Restaurante" + e.getMessage());
        }

        return listaDonoRestauranteDTO;
    }

}
