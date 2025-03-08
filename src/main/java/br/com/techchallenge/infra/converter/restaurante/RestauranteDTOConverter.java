package br.com.techchallenge.infra.converter.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteSimplesResponseDto;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteResponseDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarIdResponseDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteResponseDTO;
import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;
import br.com.techchallenge.domain.useCase.endereco.Endereco;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RestauranteDTOConverter {

    public RestauranteEntity dtoParaEntity(RestauranteRequestDTO dto) {
        return Optional.ofNullable(dto)
                .map(source -> {
                    RestauranteEntity entity = new RestauranteEntity();
                    entity.setNome(dto.nome());
                    entity.setEndereco(dto.endereco());
                    entity.setTipoCozinha(dto.tipoCozinha());
                    DonoRestauranteEntity donoRestauranteEntity = new DonoRestauranteEntity();
                    donoRestauranteEntity.setId(dto.getIdDonoRestaurante());
                    entity.setDonoRestaurante(donoRestauranteEntity);
                    return entity;
                })
                .orElse(null);
    }

    public RestauranteListarTodosResponseDTO entityParaListarTodosDto(RestauranteEntity entity) {
        return new RestauranteListarTodosResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco().toString(),
                entity.getTipoCozinha()
        );
    }

    public RestauranteListarIdResponseDTO entityParaListarIdDto(RestauranteEntity entity) {
        return new RestauranteListarIdResponseDTO(
                entity.getNome(),
                entity.getEndereco().toString(),
                entity.getTipoCozinha()
        );
    }

    public RestauranteResponseDTO entityParaResponseDto(RestauranteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new RestauranteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getTipoCozinha(),
                donoEntityParaResponseDto(entity.getDonoRestaurante())
        );
    }

    private DonoRestauranteResponseDTO donoEntityParaResponseDto(DonoRestauranteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DonoRestauranteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getEmail(),
                entity.getLogin()
        );
    }

    private DonoRestauranteSimplesResponseDto donoEntityParaSimplesResponseDto(DonoRestauranteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DonoRestauranteSimplesResponseDto(
                entity.getNome(),
                entity.getEmail(),
                entity.getEndereco()
        );
    }
    private Long id;
    private String nome;
    private Endereco endereco;
    private String tipoCozinha;
    private DonoRestaurante donoRestaurante;
    public Restaurante restauranteEntityToRestaurante(RestauranteEntity restauranteEntity) {
        return new Restaurante(
                restauranteEntity.getId(),
                restauranteEntity.getNome(),
                restauranteEntity.getEndereco(),
                restauranteEntity.getTipoCozinha()
        );

    }

    public RestauranteEntity restauranteParaEntity(Restaurante restaurante) {
        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(restaurante.getId());
        restauranteEntity.setNome(restaurante.getNome());
        restauranteEntity.setEndereco(restaurante.getEndereco());
        restauranteEntity.setTipoCozinha(restaurante.getTipoCozinha());
        return restauranteEntity;
    }

    public Restaurante dtoToRestaurante(RestauranteRequestDTO request) {
        return new Restaurante(
                null,
                request.nome(),
                request.endereco(),
                request.tipoCozinha()
        );
    }
}
