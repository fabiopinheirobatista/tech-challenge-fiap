package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteJaCadastradoException;
import br.com.techchallenge.domain.gateway.RestauranteBuscarTodosInterface;
import br.com.techchallenge.domain.gateway.RestauranteSalvarInterface;

public class CadastrarRestauranteUseCase {

    private final RestauranteSalvarInterface repositorySalvar;


    public CadastrarRestauranteUseCase(RestauranteSalvarInterface repositorySalvar) {
        this.repositorySalvar = repositorySalvar;

    }

    public Restaurante execute(Restaurante restaurante) throws RestauranteJaCadastradoException {

        // Verifica se já existe um restaurante com o mesmo nome
        /*
            boolean nomeJaExiste = repositoryBuscarPorNome.buscarPorNome(restaurante.getNome()).isPresent();
            if (nomeJaExiste) {
            throw new RestauranteJaCadastradoException("Restaurante já cadastrado com esse nome!");
        }
         */

        // Verifica se o dono do restaurante existe
        /*
        Optional<DonoRestaurante> dono = donoRestauranteRepository.buscarPorId(restaurante.getDonoId());
        if (dono.isEmpty()) {
            throw new Exception("Dono de Restaurante informado não existe!");
        }
        */


       return repositorySalvar.salvar(restaurante);
    }

}
