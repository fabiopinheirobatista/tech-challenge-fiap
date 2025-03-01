package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.helper.RestauranteHelper;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import br.com.techchallenge.shared.InternalServerErrorException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarRestaurantePorIdServiceTest {

    @InjectMocks
    private BuscarRestaurantePorIdService service;

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteMapper mapper;

    @Mock
    private RestauranteEntity restauranteEntity;

    @Mock
    private Restaurante restaurante;


    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);

        // Criando um restaurante para simular o banco
//        restauranteEntity = new RestauranteEntity();
//        restauranteEntity.setId(1L);
//        restauranteEntity.setNome("Restaurante Teste");
//
//        // Criando um restaurante mapeado
//        restaurante = new Restaurante();
//        restaurante.setId(1L);
//        restaurante.setNome("Restaurante Teste");
    }

    @Test
    void testBuscarPorIdRestauranteEncontrado() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(restauranteEntity));
        when(mapper.toRestaurante(restauranteEntity)).thenReturn(restaurante);

        // Adicionando um log para verificar se o mock está funcionando
        System.out.println("Mock retornando: " + repository.findById(id));

        // Chamando o método que estamos testando
        Optional<Restaurante> resultado = service.buscarPorId(1L);

        // Verificaçõe
        assertThat(resultado).isPresent();
        assertThat(resultado.get()).isEqualTo(restaurante);


    }

    @Test
    void testBuscarPorIdRestauranteNaoEncontrado() throws InternalServerErrorException {
        Long id = 2L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Restaurante não encontrado");
    }
}