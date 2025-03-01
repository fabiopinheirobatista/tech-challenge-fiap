package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.helper.RestauranteHelper;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class CadastrarRestauranteServiceIT {


    @Autowired
    private RestauranteRepository repository;
    @Autowired
    private CadastrarRestauranteService restauranteService;

    @Test
    void devePermitirCadastrarRestaurante() {
        var restaurante = RestauranteHelper.restaurante();

        var restauranteCadastrado = restauranteService.cadastrar(restaurante);

        assertThat(restauranteCadastrado).isNotNull();
        assertThat(restauranteCadastrado.getNome()).isEqualTo(restaurante.getNome());
        assertThat(restauranteCadastrado.getTipoCozinha()).isEqualTo(restaurante.getTipoCozinha());
    }


}
