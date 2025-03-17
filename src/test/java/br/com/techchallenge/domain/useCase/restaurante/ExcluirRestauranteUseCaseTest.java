package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteDeletarInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirRestauranteUseCaseTest {


    @InjectMocks
    private ExcluirRestauranteUseCase excluirRestauranteUseCase;

    @Mock
    private RestauranteDeletarInterface repositoryDeletar;

    @Mock
    private RestauranteBuscarPorIdInterface repositoryPorId;

    @BeforeEach
    void setUp() {
        // Initialize mocks
    }

    @Test
    void deveExcluirRestauranteQuandoEncontrado() throws RestauranteNaoEncontradoException {
        Long id = 1L;
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);

        when(repositoryPorId.buscarPorId(id)).thenReturn(Optional.of(restaurante));
        doNothing().when(repositoryDeletar).deletar(id);

        excluirRestauranteUseCase.execute(id);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long id = 1L;

        when(repositoryPorId.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(RestauranteNaoEncontradoException.class, () -> {
            excluirRestauranteUseCase.execute(id);
        });
    }

}