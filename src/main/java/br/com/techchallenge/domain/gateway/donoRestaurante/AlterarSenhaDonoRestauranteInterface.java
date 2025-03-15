package br.com.techchallenge.domain.gateway.donoRestaurante;

import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;

public interface AlterarSenhaDonoRestauranteInterface {

    DonoRestaurante alterarSenha(Long id, String email, String senhaAtual, String novaSenha);

}
