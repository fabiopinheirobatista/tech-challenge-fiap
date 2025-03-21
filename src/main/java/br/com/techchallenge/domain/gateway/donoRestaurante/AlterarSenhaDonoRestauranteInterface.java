package br.com.techchallenge.domain.gateway.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoEncontradoException;

public interface AlterarSenhaDonoRestauranteInterface {

    DonoRestaurante alterarSenha(Long id, String email, String senhaAtual, String novaSenha) throws DonoRestauranteNaoEncontradoException;

}
