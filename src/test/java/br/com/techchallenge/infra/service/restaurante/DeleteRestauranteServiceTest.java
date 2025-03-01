package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteRestauranteServiceTest {

    @Mock
    private RestauranteRepository repository;

    @InjectMocks
    private DeleteRestauranteService service;

    @Test
    void deveDeletarRestaurantePorId() {
        Long restauranteId = 1L;

        service.deletar(restauranteId);

        verify(repository, times(1)).deleteById(restauranteId);
    }
}
