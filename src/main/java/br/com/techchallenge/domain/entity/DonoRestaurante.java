package br.com.techchallenge.domain.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
