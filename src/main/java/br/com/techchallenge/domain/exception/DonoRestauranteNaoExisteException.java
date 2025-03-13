package br.com.techchallenge.domain.exception;

public class DonoRestauranteNaoExisteException extends Throwable {
    public DonoRestauranteNaoExisteException(String mensagem){
        super(mensagem);
    }
}
