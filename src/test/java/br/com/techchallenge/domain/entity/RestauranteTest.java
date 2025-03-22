package br.com.techchallenge.domain.entity;

import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RestauranteTest {

    @Mock
    private Endereco endereco;

    @Mock
    private DonoRestaurante donoRestaurante;

    @Test
    @DisplayName("Deve atualizar dados do restaurante")
    void deveAtualizarDadosDoRestaurante() {
        Restaurante restaurante = new Restaurante();
        Long id = 1L;
        String nomeInicial = "Sabor Brasileiro";
        String tipoCozinhaInicial = "Brasileira";

        String novoNome = "Sabor Brasileiro Gourmet";
        String novoTipoCozinha = "Brasileira Contemporânea";

        Endereco novoEndereco = new Endereco();
        DonoRestaurante novoDonoRestaurante = new DonoRestaurante();

        restaurante.setId(id);
        restaurante.setNome(nomeInicial);
        restaurante.setEndereco(endereco);
        restaurante.setTipoCozinha(tipoCozinhaInicial);
        restaurante.setDonoRestaurante(donoRestaurante);

        assertEquals(id, restaurante.getId());
        assertEquals(nomeInicial, restaurante.getNome());
        assertEquals(endereco, restaurante.getEndereco());
        assertEquals(tipoCozinhaInicial, restaurante.getTipoCozinha());
        assertEquals(donoRestaurante, restaurante.getDonoRestaurante());

        restaurante.setNome(novoNome);
        restaurante.setEndereco(novoEndereco);
        restaurante.setTipoCozinha(novoTipoCozinha);
        restaurante.setDonoRestaurante(novoDonoRestaurante);

        assertEquals(id, restaurante.getId());
        assertEquals(novoNome, restaurante.getNome());
        assertEquals(novoEndereco, restaurante.getEndereco());
        assertEquals(novoTipoCozinha, restaurante.getTipoCozinha());
        assertEquals(novoDonoRestaurante, restaurante.getDonoRestaurante());
    }
}