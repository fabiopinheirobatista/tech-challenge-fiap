package br.com.techchallenge.infra.adapter.repository.restaurante;

import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestauranteCadastrarRepositoryImpTest {

    @Mock
    private RestauranteRepository repositoryRestaurante;

    @Mock
    private DonoRestauranteRepository repositoryDonoRestaurante;

    @Mock
    private RestauranteDTOConverter converterRestaurante;

    @Mock
    private DonoRestauranteDTOConverter converterDonoRestaurante;

    @InjectMocks
    private RestauranteCadastrarRepositoryImp repositoryImp;

    private Restaurante restaurante;
    private RestauranteEntity restauranteEntity;
    private RestauranteEntity restauranteSalvo;
    private Restaurante restauranteDomainSalvo;

    @BeforeEach
    void setUp() {
        Long id = 1L;
        String nome = "Restaurante Teste";
        Endereco endereco = new Endereco("Endereço do Restaurante");

        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(id);
        restauranteEntity.setNome(nome);
        restauranteEntity.setEndereco(endereco);

        restauranteSalvo = new RestauranteEntity();
        restauranteSalvo.setId(id);
        restauranteSalvo.setNome(nome);
        restauranteSalvo.setEndereco(endereco);
    }
}