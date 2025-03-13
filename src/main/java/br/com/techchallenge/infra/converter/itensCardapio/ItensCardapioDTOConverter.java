package br.com.techchallenge.infra.converter.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.input.itensCardapio.ItensCardapioRequestDTO;
import br.com.techchallenge.domain.output.itensCardapio.ItensCardapioResponseDTO;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.springframework.stereotype.Component;

@Component
public class ItensCardapioDTOConverter {

    public ItensCardapio dtoToDomain(ItensCardapioRequestDTO dto) {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(dto.idRestaurante());
        return new ItensCardapio(
                null,
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.disponibilidade(),
                dto.fotoPrato(),
                dto.idRestaurante()
        );
    }

    public ItensCardapioResponseDTO domainToDto(ItensCardapio item) {
        return new ItensCardapioResponseDTO(
                item.getId(),
                item.getNome(),
                item.getDescricao(),
                item.getPreco(),
                item.getDisponibilidade(),
                item.getFotoPrato(),
                item.getIdRestaurante()
        );
    }

    public ItensCardapioEntity domainToEntity(ItensCardapio item, RestauranteEntity restaurante) {
        ItensCardapioEntity entity = new ItensCardapioEntity();
        entity.setNome(item.getNome());
        entity.setDescricao(item.getDescricao());
        entity.setPreco(item.getPreco());
        entity.setDisponibilidade(item.getDisponibilidade());
        entity.setFotoPrato(item.getFotoPrato());
        entity.setRestaurante(restaurante);
        return entity;
    }

    public ItensCardapio entityToDomain(ItensCardapioEntity entity) {
        Restaurante restaurante = null;
        if (entity.getRestaurante() != null) {
            restaurante = new Restaurante(
                    entity.getRestaurante().getId(),
                    entity.getRestaurante().getNome(),
                    null, // Endereco pode ser omitido ou convertido
                    entity.getRestaurante().getTipoCozinha(),
                    null // DonoRestaurante pode ser omitido
            );
        }

        return new ItensCardapio(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponibilidade(),
                entity.getFotoPrato(),
                entity.getRestaurante() != null ? entity.getRestaurante().getId() : null // Converte RestauranteEntity para Restaurante (domínio)
        );
    }

    public ItensCardapioEntity domainToEntity(ItensCardapio item) {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setId(item.getIdRestaurante());

        ItensCardapioEntity entity = new ItensCardapioEntity();
        entity.setId(item.getId());
        entity.setNome(item.getNome());
        entity.setDescricao(item.getDescricao());
        entity.setPreco(item.getPreco());
        entity.setDisponibilidade(item.getDisponibilidade());
        entity.setFotoPrato(item.getFotoPrato());
        entity.setRestaurante(restaurante);
        return entity;
    }


}