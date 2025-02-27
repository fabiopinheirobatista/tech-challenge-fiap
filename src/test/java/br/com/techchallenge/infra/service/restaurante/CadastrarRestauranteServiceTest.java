package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarRestauranteServiceTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private CadastrarRestauranteService cadastrarRestauranteService;

    private Restaurante restaurante;
    private RestauranteEntity restauranteEntity;

    @BeforeEach
    public void setUp() {
        // Inicializa os objetos que serão usados nos testes
        restaurante = new Restaurante();
        restaurante.setNome("Restaurante Teste");

        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setNome("Restaurante Teste");
    }

    @Test
    public void testCadastrar() {
        when(mapper.toRestauranteEntity(any(Restaurante.class))).thenReturn(restauranteEntity);
        when(repository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);
        when(mapper.toRestaurante(any(RestauranteEntity.class))).thenReturn(restaurante);

        Restaurante result = cadastrarRestauranteService.cadastrar(restaurante);

        assertEquals(restaurante.getNome(), result.getNome());
    }


}