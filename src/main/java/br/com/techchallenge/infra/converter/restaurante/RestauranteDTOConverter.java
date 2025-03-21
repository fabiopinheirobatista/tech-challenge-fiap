package br.com.techchallenge.infra.converter.restaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteResponseDTO;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteSimplesResponseDto;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarIdResponseDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteResponseDTO;
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
                entity.getEndereco(),
                entity.getTipoCozinha()
        );
    }

    public RestauranteListarIdResponseDTO entityParaListarIdDto(RestauranteEntity entity) {
        return new RestauranteListarIdResponseDTO(
                entity.getNome(),
                entity.getEndereco(),
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

    public Restaurante restauranteEntityToRestaurante(RestauranteEntity restauranteEntity) {

        DonoRestauranteEntity donoRestauranteEntity = restauranteEntity.getDonoRestaurante();

        DonoRestaurante donodonoRestaurante = convertToDonoRestaurante(donoRestauranteEntity);
        return new Restaurante(
                restauranteEntity.getId(),
                restauranteEntity.getNome(),
                restauranteEntity.getEndereco(),
                restauranteEntity.getTipoCozinha(),
                donodonoRestaurante
        );

    }

    private DonoRestaurante convertToDonoRestaurante(DonoRestauranteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DonoRestaurante(
                entity.getId(),
                entity.getNome(),
                null,
                entity.getEmail(),
                entity.getLogin(),
                entity.getSenha(),
                entity.getDataUltimaAlteracao()
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
        DonoRestaurante donoRestaurante = new DonoRestaurante();
        donoRestaurante.setId(request.getIdDonoRestaurante());
        return new Restaurante(
                null,
                request.nome(),
                request.endereco(),
                request.tipoCozinha(),
                donoRestaurante
        );
    }

    public RestauranteListarTodosResponseDTO restauranteParaResponseDto(Restaurante restaurante) {
        return new RestauranteListarTodosResponseDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getTipoCozinha()
        );

    }
}
