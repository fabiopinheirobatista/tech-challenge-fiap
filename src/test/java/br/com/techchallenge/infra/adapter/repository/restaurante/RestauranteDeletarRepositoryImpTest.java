package br.com.techchallenge.infra.adapter.repository.restaurante;

import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

class RestauranteDeletarRepositoryImpTest {

    @Mock
    private RestauranteRepository repository;

    @InjectMocks
    private RestauranteDeletarRepositoryImp deletarRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveDeletarRestaurante_QuandoIdExistir() {
        Long id = 1L;

        doNothing().when(repository).deleteById(id);

        deletarRepository.deletar(id);

        verify(repository, times(1)).deleteById(id);
    }
}