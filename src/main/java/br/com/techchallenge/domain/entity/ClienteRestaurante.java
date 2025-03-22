package br.com.techchallenge.domain.entity;


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

    public ClienteRestaurante(long l, String s, String s1) {
        this.id = l;
        this.nome = s;
        this.email = s1;
    }
}
