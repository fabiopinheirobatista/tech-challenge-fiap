package br.com.techchallenge.domain.entity;

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
    private DonoRestaurante donoRestaurante;

}