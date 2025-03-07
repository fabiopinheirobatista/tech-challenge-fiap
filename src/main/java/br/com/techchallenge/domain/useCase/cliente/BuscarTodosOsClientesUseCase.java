package br.com.techchallenge.domain.useCase.cliente;


import br.com.techchallenge.domain.clienteRestaurante.ClienteRestaurante;
import br.com.techchallenge.domain.dto.clienteRestaurante.ClienteRestauranteRequestDto;
import br.com.techchallenge.domain.dto.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.domain.gateway.BuscarClienteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class BuscarTodosOsClientesUseCase {

    private final BuscarClienteInterface buscarClienteInterface;

    public List<ClienteRestauranteResponseDto> execute() {

        List<ClienteRestauranteResponseDto> listaClienteDTO = new ArrayList<>();

        try {
            List<ClienteRestaurante> listaCliente = buscarClienteInterface.buscarTodos();

            for (ClienteRestaurante cliente : listaCliente) {
                listaClienteDTO.add(new ClienteRestauranteResponseDto(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getLogin()
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os clientes" + e.getMessage());
        }

        return listaClienteDTO;
    }
}
