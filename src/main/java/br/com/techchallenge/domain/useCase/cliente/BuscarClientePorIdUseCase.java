package br.com.techchallenge.domain.useCase.cliente;


import br.com.techchallenge.domain.entity.clienteRestaurante.ClienteRestaurante;
import br.com.techchallenge.domain.dto.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.domain.gateway.clienteRestaurante.BuscarClientePorIdInterface;
import br.com.techchallenge.infra.adapter.repository.BuscarClientePorIdRepositoryImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BuscarClientePorIdUseCase {

    private final BuscarClientePorIdInterface clienteRestauranteInterface;

    public ClienteRestauranteResponseDto execute(Long id) {

        ClienteRestauranteResponseDto clienteDTO = null;

        try {
            ClienteRestaurante cliente = clienteRestauranteInterface.buscarPorId(id);
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
