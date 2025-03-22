package br.com.techchallenge.infra.entity;

import br.com.techchallenge.domain.entity.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestauranteEntityTest {

    @Mock
    private DonoRestauranteEntity donoRestauranteMock;

    @Mock
    private Endereco enderecoMock;

    @Test
    @DisplayName("Deve criar uma entidade de restaurante com construtor vazio")
    void deveCriarEntidadeRestauranteComConstrutorVazio() {
        RestauranteEntity restaurante = new RestauranteEntity();

        assertNotNull(restaurante);
        assertNull(restaurante.getId());
        assertNull(restaurante.getNome());
        assertNull(restaurante.getEndereco());
        assertNull(restaurante.getTipoCozinha());
        assertNull(restaurante.getDonoRestaurante());
    }

    @Test
    @DisplayName("Deve criar uma entidade de restaurante com todos os parâmetros")
    void deveCriarEntidadeRestauranteComTodosParametros() {
        Long id = 1L;
        String nome = "Restaurante Teste";
        String tipoCozinha = "Italiana";

        RestauranteEntity restaurante = new RestauranteEntity(id, nome, enderecoMock, tipoCozinha, donoRestauranteMock);

        assertNotNull(restaurante);
        assertEquals(id, restaurante.getId());
        assertEquals(nome, restaurante.getNome());
        assertEquals(enderecoMock, restaurante.getEndereco());
        assertEquals(tipoCozinha, restaurante.getTipoCozinha());
        assertEquals(donoRestauranteMock, restaurante.getDonoRestaurante());
    }

    @Test
    @DisplayName("Deve alterar os valores dos atributos")
    void deveAlterarValoresAtributos() {
        RestauranteEntity restaurante = new RestauranteEntity();

        Long id = 1L;
        String nome = "Restaurante Teste";
        String tipoCozinha = "Italiana";

        restaurante.setId(id);
        restaurante.setNome(nome);
        restaurante.setEndereco(enderecoMock);
        restaurante.setTipoCozinha(tipoCozinha);
        restaurante.setDonoRestaurante(donoRestauranteMock);

        assertEquals(id, restaurante.getId());
        assertEquals(nome, restaurante.getNome());
        assertEquals(enderecoMock, restaurante.getEndereco());
        assertEquals(tipoCozinha, restaurante.getTipoCozinha());
        assertEquals(donoRestauranteMock, restaurante.getDonoRestaurante());
    }

    @Test
    @DisplayName("Deve verificar associação com dono do restaurante")
    void deveVerificarAssociacaoComDonoRestaurante() {
        RestauranteEntity restaurante = new RestauranteEntity();

        assertNull(restaurante.getDonoRestaurante());

        restaurante.setDonoRestaurante(donoRestauranteMock);

        assertNotNull(restaurante.getDonoRestaurante());
        assertEquals(donoRestauranteMock, restaurante.getDonoRestaurante());
    }

    @Test
    @DisplayName("Deve verificar associação com endereço")
    void deveVerificarAssociacaoComEndereco() {
        RestauranteEntity restaurante = new RestauranteEntity();

        assertNull(restaurante.getEndereco());

        restaurante.setEndereco(enderecoMock);

        assertNotNull(restaurante.getEndereco());
        assertEquals(enderecoMock, restaurante.getEndereco());
    }
}