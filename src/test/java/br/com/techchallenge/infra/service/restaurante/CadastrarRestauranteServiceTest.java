package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.helper.RestauranteHelper;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.Assert.isInstanceOf;


@ExtendWith(MockitoExtension.class)
class CadastrarRestauranteServiceTest {

    @Mock
    private RestauranteRepository repository;
    private CadastrarRestauranteService restauranteService;
    @Mock
    private RestauranteMapper mapper;
    @Mock
    private RestauranteEntity restauranteEntity;


    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        restauranteService = new CadastrarRestauranteService(repository, mapper);
    }

    @AfterEach
    void close() throws Exception {
        mock.close();
    }

    @Test
    public void testCadastrar() {
        var restaurante = RestauranteHelper.restaurante();
        when(mapper.toRestauranteEntity(any(Restaurante.class))).thenReturn(restauranteEntity);
        when(repository.save(any(RestauranteEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        when(mapper.toRestaurante(any(RestauranteEntity.class))).thenReturn(restaurante);

        var restauranteCadastrado = restauranteService.cadastrar(restaurante);

        assertThat(restauranteCadastrado).isNotNull();
        assertThat(restauranteCadastrado.getNome()).isEqualTo(restaurante.getNome());
        assertThat(restauranteCadastrado.getTipoCozinha()).isEqualTo(restaurante.getTipoCozinha());
        assertThat(restauranteCadastrado).isInstanceOf(Restaurante.class);

    }


}

//when(mapper.toRestauranteEntity(any(Restaurante.class))).thenReturn(restauranteEntity);
//when(repository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);
//when(mapper.toRestaurante(any(RestauranteEntity.class))).thenReturn(restaurante);