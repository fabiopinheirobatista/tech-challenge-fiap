package br.com.techchallenge.infra.service.restaurante;


import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import br.com.techchallenge.shared.InternalServerErrorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class BuscarRestaurantePorIdIT {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private BuscarRestaurantePorIdService buscarRestaurantePorIdService;

    @Test
    void deveBuscarRestaurantePorId() throws InternalServerErrorException {

        var id = 1L;

        var restauranteOptional = buscarRestaurantePorIdService.buscarPorId(id);

        assertThat(restauranteOptional).isPresent();

        var restaurante = restauranteOptional.get();

        assertThat(restaurante).isNotNull();
        assertThat(restaurante).isInstanceOf(Restaurante.class);

    }

}
