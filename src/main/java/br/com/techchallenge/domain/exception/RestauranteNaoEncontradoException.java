package br.com.techchallenge.domain.exception;

public class RestauranteNaoEncontradoException extends Throwable {
    public RestauranteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
