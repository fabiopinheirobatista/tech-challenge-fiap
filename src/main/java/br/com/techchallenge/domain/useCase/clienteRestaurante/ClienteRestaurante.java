package br.com.techchallenge.domain.useCase.clienteRestaurante;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteRestaurante {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
}
