package br.com.techchallenge.domain.useCase.clienteRestaurante;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteJaCadastradoException;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.clienteRestaurante.SalvarClienteInterface;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SalvarClienteUseCase {

    private final SalvarClienteInterface clienteRestauranteInterface;
    private final ClienteRestauranteRepository clienteRestauranteRepository;

   public ClienteRestaurante atualizar(ClienteRestauranteEntity clienteRestauranteEntity) throws ClienteNaoEncontradoException {
       ClienteRestauranteEntity clienteExistente = clienteRestauranteRepository.findById(clienteRestauranteEntity.getId())
               .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente de Restaurante não encontrado"));

       clienteExistente.setNome(clienteRestauranteEntity.getNome());
       clienteExistente.setEmail(clienteRestauranteEntity.getEmail());
       clienteExistente.setLogin(clienteRestauranteEntity.getLogin());
       clienteExistente.setSenha(clienteRestauranteEntity.getSenha());

         return clienteRestauranteInterface.atualizar(clienteExistente);
    }

    public ClienteRestaurante cadastrar(ClienteRestauranteEntity clienteRestauranteEntity) throws ClienteJaCadastradoException {

        if(clienteRestauranteRepository.existsByEmail(clienteRestauranteEntity.getEmail())) {
            throw new ClienteJaCadastradoException("Cliente já cadastrado com este email.");
        }

        return clienteRestauranteInterface.cadastrar(clienteRestauranteEntity);
    }

}
