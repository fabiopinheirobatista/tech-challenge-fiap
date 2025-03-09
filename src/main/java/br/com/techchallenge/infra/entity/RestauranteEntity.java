package br.com.techchallenge.infra.entity;

import br.com.techchallenge.domain.entity.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "dono_restaurante_id")
    private DonoRestauranteEntity donoRestaurante;

}
