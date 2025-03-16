package br.com.techchallenge.infra.adapter.repository.cliente;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.gateway.clienteRestaurante.SalvarClienteInterface;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SalvarClienteRepositoryImpl implements SalvarClienteInterface {

    private final ClienteRestauranteRepository clienteRestauranteRepository;


    @Override
    public ClienteRestaurante atualizar(ClienteRestauranteEntity clienteRestauranteEntity) {
        clienteRestauranteRepository.save(clienteRestauranteEntity);

        return new ClienteRestaurante(
            clienteRestauranteEntity.getId(),
            clienteRestauranteEntity.getNome(),
            clienteRestauranteEntity.getEmail(),
            clienteRestauranteEntity.getLogin(),
            clienteRestauranteEntity.getSenha()
        );


    }

    @Override
    public ClienteRestaurante cadastrar(ClienteRestauranteEntity clienteRestauranteEntity) {
        clienteRestauranteRepository.save(clienteRestauranteEntity);

        return new ClienteRestaurante(
            clienteRestauranteEntity.getId(),
            clienteRestauranteEntity.getNome(),
            clienteRestauranteEntity.getEmail(),
            clienteRestauranteEntity.getLogin(),
            clienteRestauranteEntity.getSenha()
        );
    }
}
