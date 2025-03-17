package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoExisteException;
import br.com.techchallenge.domain.exception.RestauranteJaCadastradoException;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteSalvarInterface;
import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarRestauranteUseCaseTest {

    @InjectMocks
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @Mock
    private RestauranteSalvarInterface repositorySalvar;


    @Test
    void deveCadastrarRestauranteComSucesso() throws RestauranteJaCadastradoException, DonoRestauranteNaoExisteException {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante Teste");
        DonoRestaurante dono = new DonoRestaurante();
        dono.setId(1L);
        restaurante.setDonoRestaurante(dono);

        when(repositorySalvar.buscarPorNome(restaurante.getNome())).thenReturn(false);
        when(repositorySalvar.buscarPorIdDonoRestaurante(dono.getId())).thenReturn(Optional.of(dono));
        when(repositorySalvar.salvar(restaurante)).thenReturn(restaurante);

        Restaurante resultado = cadastrarRestauranteUseCase.execute(restaurante);

        assertEquals(restaurante, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteJaCadastrado() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante Teste");

        when(repositorySalvar.buscarPorNome(restaurante.getNome())).thenReturn(true);

        assertThrows(RestauranteJaCadastradoException.class, () -> {
            cadastrarRestauranteUseCase.execute(restaurante);
        });
    }

    @Test
    void deveLancarExcecaoQuandoDonoRestauranteNaoExiste() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante Teste");
        DonoRestaurante dono = new DonoRestaurante();
        dono.setId(1L);
        restaurante.setDonoRestaurante(dono);

        when(repositorySalvar.buscarPorNome(restaurante.getNome())).thenReturn(false);
        when(repositorySalvar.buscarPorIdDonoRestaurante(dono.getId())).thenReturn(Optional.empty());

        assertThrows(DonoRestauranteNaoExisteException.class, () -> {
            cadastrarRestauranteUseCase.execute(restaurante);
        });
    }
}