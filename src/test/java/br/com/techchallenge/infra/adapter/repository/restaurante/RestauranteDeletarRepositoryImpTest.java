package br.com.techchallenge.infra.adapter.repository.restaurante;

import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestauranteDeletarRepositoryImpTest {

    @Mock
    private RestauranteRepository repository;

    @InjectMocks
    private RestauranteDeletarRepositoryImp restauranteDeletarRepository;

    private Long idRestaurante;

    @BeforeEach
    void configurar() {
        idRestaurante = 1L;
    }

    @Test
    @DisplayName("Deve deletar um restaurante com sucesso")
    void deveDeletarRestauranteComSucesso() {
        doNothing().when(repository).deleteById(anyLong());

        restauranteDeletarRepository.deletar(idRestaurante);

        verify(repository).deleteById(idRestaurante);
    }
}