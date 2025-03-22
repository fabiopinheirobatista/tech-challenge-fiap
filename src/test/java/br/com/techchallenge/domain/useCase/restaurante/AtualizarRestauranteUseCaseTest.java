package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteAtualizarInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarRestauranteUseCaseTest {

    @Mock
    private RestauranteAtualizarInterface atualizarRepository;

    @Mock
    private RestauranteBuscarPorIdInterface buscarPorIdRepository;

    private AtualizarRestauranteUseCase useCase;

    private final Long restauranteId = 1L;
    private final String nomeOriginal = "Restaurante Original";
    private final String nomeAtualizado = "Restaurante Atualizado";
    private final String endereco = "Endereço do Restaurante";
    private final Restaurante restauranteOriginal = new Restaurante(restauranteId, nomeOriginal, endereco);
    private final Restaurante restauranteAtualizado = new Restaurante(restauranteId, nomeAtualizado, endereco);

    @BeforeEach
    public void setUp() {
        useCase = new AtualizarRestauranteUseCase(atualizarRepository, buscarPorIdRepository);
    }

    @Test
    @DisplayName("Deve atualizar o restaurante com sucesso")
    void deveAtualizarRestauranteComSucesso() throws RestauranteNaoEncontradoException {
        when(buscarPorIdRepository.buscarPorId(restauranteId)).thenReturn(Optional.of(restauranteOriginal));
        when(atualizarRepository.update(restauranteAtualizado)).thenReturn(restauranteAtualizado);

        Restaurante resultado = useCase.execute(restauranteId, restauranteAtualizado);

        assertEquals(nomeAtualizado, resultado.getNome());
        assertEquals(restauranteId, resultado.getId());
        assertEquals(endereco, resultado.getEndereco());
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não for encontrado")
    void deveLancarExcecaoQuandoRestauranteNaoForEncontrado() {
        when(buscarPorIdRepository.buscarPorId(restauranteId)).thenReturn(Optional.empty());

        assertThrows(RestauranteNaoEncontradoException.class, () ->
                useCase.execute(restauranteId, restauranteAtualizado)
        );
    }
}