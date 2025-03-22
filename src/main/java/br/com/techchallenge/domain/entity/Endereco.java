package br.com.techchallenge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Endereco(String rua, String numero, String bairro, String cidade, String estado) {
        this.logradouro = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getRua() {
        return logradouro;
    }

    public void setRua(String rua) {
        this.logradouro = rua;
    }

    public Endereco(String rua, String numero, String cidade) {
        this.logradouro = rua;
        this.numero = numero;
        this.cidade = cidade;
    }

    public Endereco(String enderecoCompleto) {
        String[] partes = enderecoCompleto.split(", ");
        this.logradouro = partes[0];
    }
}
