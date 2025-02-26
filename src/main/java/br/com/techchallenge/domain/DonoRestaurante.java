package br.com.techchallenge.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonoRestaurante {

    private Long id;
    private String nome;
    private String endereco;
    private String email;
    private String login;
    private String senha;
    private LocalDate dataUltimaAlteracao;
}
