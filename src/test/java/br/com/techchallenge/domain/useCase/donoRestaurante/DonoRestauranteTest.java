package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.entity.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteTest {

    @Mock
    private Endereco endereco;

    @Test
    @DisplayName("Deve atualizar senha e data de alteração corretamente")
    void deveAtualizarSenhaEDataDeAlteracaoCorretamente() {
        LocalDate dataInicial = LocalDate.now().minusDays(10);
        String senhaAntiga = "senhaAntiga123";

        DonoRestaurante donoRestaurante = new DonoRestaurante(
                1L,
                "João Silva",
                endereco,
                "joao@email.com",
                "joaosilva",
                senhaAntiga,
                dataInicial
        );

        String novaSenha = "novaSenha456";
        LocalDate dataAtual = LocalDate.now();

        donoRestaurante.setSenha(novaSenha);
        donoRestaurante.setDataUltimaAlteracao(dataAtual);

        assertEquals(novaSenha, donoRestaurante.getSenha());
        assertEquals(dataAtual, donoRestaurante.getDataUltimaAlteracao());
        assertNotEquals(senhaAntiga, donoRestaurante.getSenha());
        assertNotEquals(dataInicial, donoRestaurante.getDataUltimaAlteracao());
    }
}