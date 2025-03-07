package br.com.techchallenge.domain.exception;

public class InternalServerErrorException extends Exception {

    private String message;

    public InternalServerErrorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
