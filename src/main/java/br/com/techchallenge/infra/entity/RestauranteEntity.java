package br.com.techchallenge.infra.entity;

import br.com.techchallenge.domain.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurantes")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
