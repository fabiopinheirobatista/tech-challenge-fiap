package br.com.techchallenge.domain.useCase.restaurante;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteAtualizarInterface;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteBuscarPorIdInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AtualizarRestauranteUseCaseTest {

    private RestauranteAtualizarInterface atualizarRepository;
    private RestauranteBuscarPorIdInterface buscarPorIdRepository;
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @BeforeEach
    void setUp() {
        atualizarRepository = mock(RestauranteAtualizarInterface.class);
        buscarPorIdRepository = mock(RestauranteBuscarPorIdInterface.class);
        atualizarRestauranteUseCase = new AtualizarRestauranteUseCase(atualizarRepository, buscarPorIdRepository);
    }

    @Test
    void deveAtualizarRestauranteQuandoExistir() throws RestauranteNaoEncontradoException {
        Restaurante restaurante = new Restaurante(1L, "Restaurante Atualizado", null, "Rua Atualizada", null);
        when(buscarPorIdRepository.buscarPorId(1L)).thenReturn(Optional.of(restaurante));
        when(atualizarRepository.update(restaurante)).thenReturn(restaurante);

        Restaurante resultado = atualizarRestauranteUseCase.execute(1L, restaurante);

        assertNotNull(resultado);
        assertEquals("Restaurante Atualizado", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante Inexistente", null, "Rua Inexistente", null);
        when(buscarPorIdRepository.buscarPorId(1L)).thenReturn(Optional.empty());

        RestauranteNaoEncontradoException exception = assertThrows(RestauranteNaoEncontradoException.class, () -> {
            atualizarRestauranteUseCase.execute(1L, restaurante);
        });

        assertEquals("Restaurante não encontrado", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoHouverErroNoRepositório() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante Erro", null, "Rua Erro", null);
        when(buscarPorIdRepository.buscarPorId(1L)).thenReturn(Optional.of(restaurante));
        when(atualizarRepository.update(restaurante)).thenThrow(new RuntimeException("Erro ao atualizar restaurante"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            atualizarRestauranteUseCase.execute(1L, restaurante);
        });

        assertEquals("Erro ao atualizar restaurante", exception.getMessage());
    }
}