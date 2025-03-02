package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import br.com.techchallenge.shared.InternalServerErrorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class BuscarRestaurantesServiceIT {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private BuscarRestaurantesService buscarRestaurantesService;

    @Test
    void deveBuscarListaRestaurantes() {

        List<Restaurante> restaurantes = buscarRestaurantesService.buscarTodos();

        assertThat(restaurantes).isNotNull();
        assertThat(restaurantes).isNotEmpty();
        assertThat(restaurantes).hasSizeGreaterThan(1)
                .allMatch(restaurante -> restaurante instanceof Restaurante);
    }
}
