package br.com.techchallenge.helper;

import br.com.techchallenge.domain.Restaurante;

public abstract class RestauranteHelper {

    public static Restaurante restaurante() {
        return new Restaurante(1L, "Restaurante A", null, "Italiana", null);
    }
}
