package br.com.techchallenge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "donos_restaurante")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonoRestaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String endereco;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(name = "data_ultima_alteracao")
    private LocalDate dataUltimaAlteracao;

    public DonoRestaurante(String nome, String endereco, String email, String login, String senha, LocalDate createdAt) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAlteracao = createdAt;
    }

}
