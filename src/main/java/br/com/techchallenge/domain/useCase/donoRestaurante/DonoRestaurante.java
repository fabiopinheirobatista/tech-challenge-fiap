package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.domain.entity.Endereco;
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
    private Endereco endereco;
    private String email;
    private String login;
    private String senha;
    private LocalDate dataUltimaAlteracao;
}
