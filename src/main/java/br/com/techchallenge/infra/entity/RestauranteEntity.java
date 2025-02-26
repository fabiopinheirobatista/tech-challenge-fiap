package br.com.techchallenge.infra.entity;

import br.com.techchallenge.domain.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurantes")
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false)
    private String tipoCozinha;

    @ManyToOne
    @JoinColumn(name = "dono_restaurante_id")
    private DonoRestauranteEntity donoRestaurante;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public DonoRestauranteEntity getDonoRestaurante() {
        return donoRestaurante;
    }

    public void setDonoRestaurante(DonoRestauranteEntity donoRestaurante) {
        this.donoRestaurante = donoRestaurante;
    }
}
