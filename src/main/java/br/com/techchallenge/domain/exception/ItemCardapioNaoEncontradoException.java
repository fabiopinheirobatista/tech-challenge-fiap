package br.com.techchallenge.domain.exception;

public class ItemCardapioNaoEncontradoException extends RuntimeException {

    public ItemCardapioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}