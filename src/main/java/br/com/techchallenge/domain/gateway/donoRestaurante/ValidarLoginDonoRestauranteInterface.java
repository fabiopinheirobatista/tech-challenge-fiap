package br.com.techchallenge.domain.gateway.donoRestaurante;

public interface ValidarLoginDonoRestauranteInterface {

    boolean validarLogin(Long id, String login, String senha);

}
