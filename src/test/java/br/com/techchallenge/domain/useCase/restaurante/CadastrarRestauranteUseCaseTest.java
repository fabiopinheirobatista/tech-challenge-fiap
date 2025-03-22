package br.com.techchallenge.domain.useCase.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoExisteException;
import br.com.techchallenge.domain.exception.RestauranteJaCadastradoException;
import br.com.techchallenge.domain.gateway.restaurante.RestauranteSalvarInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CadastrarRestauranteUseCaseTest {

    @Mock
    private RestauranteSalvarInterface gateway;

    private CadastrarRestauranteUseCase useCase;

    private final Restaurante restaurante = new Restaurante(null, "Nome do Restaurante", "Endereço do Restaurante");

    @BeforeEach
    public void setUp() {
        useCase = new CadastrarRestauranteUseCase(gateway);
    }

    @Test
    @DisplayName("Deve cadastrar o restaurante com sucesso")
    void deveCadastrarRestauranteComSucesso() throws RestauranteJaCadastradoException, DonoRestauranteNaoExisteException {
        useCase.execute(restaurante);
        verify(gateway).salvar(restaurante);
    }
}