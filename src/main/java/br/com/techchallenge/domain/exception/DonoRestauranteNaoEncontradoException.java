package br.com.techchallenge.domain.exception;

public class DonoRestauranteNaoEncontradoException extends Throwable {
    public DonoRestauranteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
