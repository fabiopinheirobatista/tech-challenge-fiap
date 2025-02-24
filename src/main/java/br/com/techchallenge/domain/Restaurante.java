package br.com.techchallenge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurante {

    private Long id;
    private String nome;
    private Endereco endereco;
    private String tipoCozinha;

}