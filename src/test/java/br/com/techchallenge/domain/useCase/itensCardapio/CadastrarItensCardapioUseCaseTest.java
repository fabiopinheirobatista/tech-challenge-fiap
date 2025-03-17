package br.com.techchallenge.domain.useCase.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.itensCardapio.ItensCardapioSalvarInterface;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarItensCardapioUseCaseTest {

    @InjectMocks
    private CadastrarItensCardapioUseCase cadastrarItensCardapioUseCase;

    @Mock
    private ItensCardapioSalvarInterface repositorySalvar;


    @Test
    void deveCadastrarItemCardapio() throws RestauranteNaoEncontradoException {
        ItensCardapio item = new ItensCardapio();
        item.setIdRestaurante(1L);

        RestauranteEntity restauranteEntity = new RestauranteEntity();
        when(repositorySalvar.buscarPorId(anyLong())).thenReturn(Optional.of(restauranteEntity));

        ItensCardapio itemSalvo = new ItensCardapio();
        when(repositorySalvar.salvar(any(ItensCardapio.class))).thenReturn(itemSalvo);

        ItensCardapio resultado = cadastrarItensCardapioUseCase.execute(item);

        assertNotNull(resultado);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExiste() {
        when(repositorySalvar.buscarPorId(anyLong())).thenReturn(Optional.empty());

        ItensCardapio item = new ItensCardapio();
        item.setIdRestaurante(1L);

        RestauranteNaoEncontradoException exception = assertThrows(RestauranteNaoEncontradoException.class, () -> {
            cadastrarItensCardapioUseCase.execute(item);
        });

        assertEquals("Id do Restaurante não encontrado", exception.getMessage());
    }

}