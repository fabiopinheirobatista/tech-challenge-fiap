package br.com.techchallenge.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String rua, String numero, String cidade) {
        this.logradouro = rua;
        this.numero = numero;
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }
}
