package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Endereco;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.helper.RestauranteHelper;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarRestauranteServiceTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private AtualizarRestauranteService service;

    @Test
    void deveAtualizarRestauranteQuandoExistente() {
        // 🔹 Arrange (Configuração do teste)
        Restaurante restaurante = new Restaurante(1L, "Novo Nome", null, "Mexicana", null);
        RestauranteEntity restauranteEntity = new RestauranteEntity(1L, "Antigo Nome",  null, "Italiana", null);
        RestauranteEntity restauranteAtualizado = new RestauranteEntity(1L, "Novo Nome",  null, "Mexicana", null);

        when(repository.findById(1L)).thenReturn(Optional.of(restauranteEntity));
        when(repository.save(any(RestauranteEntity.class))).thenReturn(restauranteAtualizado);
        when(mapper.toRestaurante(restauranteAtualizado)).thenReturn(restaurante);

        // 🔹 Act (Execução do método que queremos testar)
        Restaurante result = service.atualizar(restaurante);

        // 🔹 Assert (Verificações)
        assertNotNull(result);
        assertEquals("Novo Nome", result.getNome());
        assertEquals("Mexicana", result.getTipoCozinha());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(RestauranteEntity.class));
        verify(mapper, times(1)).toRestaurante(restauranteAtualizado);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        // 🔹 Arrange
        Restaurante restaurante = RestauranteHelper.restaurante();

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // 🔹 Act & Assert
        assertThrows(EntityNotFoundException.class, () -> service.atualizar(restaurante));

        verify(repository, times(1)).findById(anyLong());
        verify(repository, never()).save(any(RestauranteEntity.class));
        verify(mapper, never()).toRestaurante(any());
    }
}
