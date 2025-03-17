package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoExisteException;
import br.com.techchallenge.domain.exception.RestauranteJaCadastradoException;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteSalvarInterface;
import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CadastrarRestauranteUseCase {

    private final RestauranteSalvarInterface repositorySalvar;


    public CadastrarRestauranteUseCase(RestauranteSalvarInterface repositorySalvar) {
        this.repositorySalvar = repositorySalvar;

    }

    public Restaurante execute(Restaurante restaurante) throws RestauranteJaCadastradoException, DonoRestauranteNaoExisteException {

        // Verifica se já existe um restaurante com o mesmo nome
        boolean nomeJaExiste = repositorySalvar.buscarPorNome(restaurante.getNome());

        if (nomeJaExiste) throw new RestauranteJaCadastradoException("Restaurante já cadastrado com esse nome!");

        // Verifica se o dono do restaurante existe
        Optional<DonoRestaurante> dono = repositorySalvar.buscarPorIdDonoRestaurante(restaurante.getDonoRestaurante().getId());
        if (dono.isEmpty()) {
            throw new DonoRestauranteNaoExisteException("Dono de Restaurante informado não existe!");
        }

       return repositorySalvar.salvar(restaurante);
    }

}
