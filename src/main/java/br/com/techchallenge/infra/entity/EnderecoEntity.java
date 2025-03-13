package br.com.techchallenge.infra.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoEntity {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public String getRua() {
        return logradouro;
    }

    public void setRua(String rua) {
        this.logradouro = rua;
    }

    public EnderecoEntity(String rua, String numero, String cidade) {
        this.logradouro = rua;
        this.numero = numero;
        this.cidade = cidade;
    }

    public EnderecoEntity(String enderecoCompleto) {
        String[] partes = enderecoCompleto.split(", ");
        this.logradouro = partes[0];
    }
}
