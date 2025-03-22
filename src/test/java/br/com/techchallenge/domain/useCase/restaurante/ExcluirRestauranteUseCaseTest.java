package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteDeletarInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirRestauranteUseCaseTest {

    @Mock
    private RestauranteDeletarInterface repositoryDeletar;

    @Mock
    private RestauranteBuscarPorIdInterface repositoryPorId;

    private ExcluirRestauranteUseCase useCase;

    private final Long restauranteId = 1L;
    private final Restaurante restaurante = new Restaurante(restauranteId, "Nome do Restaurante", "Endereço do Restaurante");

    @BeforeEach
    public void setUp() {
        useCase = new ExcluirRestauranteUseCase(repositoryDeletar, repositoryPorId);
    }

    @Test
    @DisplayName("Deve excluir o restaurante com sucesso")
    void deveExcluirRestauranteComSucesso() throws RestauranteNaoEncontradoException {
        when(repositoryPorId.buscarPorId(restauranteId)).thenReturn(Optional.of(restaurante));

        assertDoesNotThrow(() -> useCase.execute(restauranteId));

        verify(repositoryDeletar).deletar(restauranteId);
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não for encontrado")
    void deveLancarExcecaoQuandoRestauranteNaoForEncontrado() {
        when(repositoryPorId.buscarPorId(restauranteId)).thenReturn(Optional.empty());

        assertThrows(RestauranteNaoEncontradoException.class, () -> useCase.execute(restauranteId));
    }
}