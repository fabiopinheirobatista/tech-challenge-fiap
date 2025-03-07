package br.com.techchallenge.domain.useCase.cliente;


import br.com.techchallenge.domain.clienteRestaurante.ClienteRestaurante;
import br.com.techchallenge.domain.dto.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.domain.gateway.BuscarClienteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BuscarClientePorIdUseCase {

    private final BuscarClienteInterface buscarClienteInterface;

    public ClienteRestauranteResponseDto execute(Long id) {

        ClienteRestauranteResponseDto clienteDTO = null;

        try {
            ClienteRestaurante cliente = buscarClienteInterface.buscarPorId(id);
            clienteDTO = new ClienteRestauranteResponseDto(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getLogin()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar cliente por id" + e.getMessage());
        }

        return clienteDTO;
    }


}
