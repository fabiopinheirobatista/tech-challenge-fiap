package br.com.techchallenge.domain;

public class Restaurante {
    private Long id;
    private String nome;
    private String endereco;
    private String tipoCozinha;

    public Restaurante() {
    }

    public Restaurante(Long id, String nome, String endereco, String tipoCozinha, DonoRestaurante donoRestaurante) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

}
