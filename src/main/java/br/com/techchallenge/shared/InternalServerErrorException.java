package br.com.techchallenge.shared;

public class InternalServerErrorException extends Exception {

    private String message;

    public InternalServerErrorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
