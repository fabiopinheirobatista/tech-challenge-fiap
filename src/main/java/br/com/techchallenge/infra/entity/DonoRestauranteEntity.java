package br.com.techchallenge.infra.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "donos_restaurante")
@AllArgsConstructor
@NoArgsConstructor
public class DonoRestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(name = "data_ultima_alteracao")
    private LocalDate dataUltimaAlteracao;

}
