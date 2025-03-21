package br.com.techchallenge.infra.converter.donoRestaurante;

import br.com.techchallenge.domain.entity.DonoRestaurante;
import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteCadastrarRequestDTO;
import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteRequestDTO;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteListarIdResponseDTO;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteListarTodosResponseDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DonoRestauranteDTOConverter {

    public DonoRestauranteEntity dtoParaEntity(DonoRestauranteRequestDTO dto) {
        return dtoParaEntity(null, dto);
    }

    public DonoRestauranteEntity dtoParaEntity(Long id, DonoRestauranteRequestDTO dto) {
        return Optional.ofNullable(dto)
                .map(source -> {
                    DonoRestauranteEntity entity = new DonoRestauranteEntity();
                    entity.setId(id);
                    entity.setNome(dto.nome());
                    entity.setEmail(dto.email());
                    entity.setEndereco(dto.endereco().toString());
                    entity.setLogin(dto.login());
                    entity.setDataUltimaAlteracao(LocalDate.now());
                    return entity;
                })
                .orElse(null);
    }

    public DonoRestauranteListarTodosResponseDTO entityParaListarTodosDto(DonoRestauranteEntity entity) {
        return new DonoRestauranteListarTodosResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco().toString(),
                entity.getEmail(),
                entity.getLogin()
        );
    }

    public DonoRestauranteListarIdResponseDTO entityParaListarIdDto(DonoRestauranteEntity entity) {
        return new DonoRestauranteListarIdResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getEmail(),
                entity.getLogin()
        );
    }

    public DonoRestauranteEntity dtoComSenhaParaEntity(DonoRestauranteCadastrarRequestDTO dto) {
        return Optional.ofNullable(dto)
                .map(source -> {
                    DonoRestauranteEntity entity = new DonoRestauranteEntity();
                    entity.setNome(dto.getNome());
                    entity.setEmail(dto.getEmail());
                    entity.setEndereco(dto.getEndereco().toString());
                    entity.setLogin(dto.getLogin());
                    entity.setSenha(dto.getSenha());
                    entity.setDataUltimaAlteracao(LocalDate.now());
                    return entity;
                })
                .orElse(null);
    }

    public DonoRestauranteListarIdResponseDTO entityParaDonoRestaurante2(DonoRestauranteEntity donoRestauranteEntity) {
        return new DonoRestauranteListarIdResponseDTO(
                donoRestauranteEntity.getId(),
                donoRestauranteEntity.getNome(),
                donoRestauranteEntity.getEndereco(),
                donoRestauranteEntity.getEmail(),
                donoRestauranteEntity.getLogin()
        );
    }

    public DonoRestaurante entityParaDonoRestaurante(DonoRestauranteEntity donoRestauranteEntity) {
        return new DonoRestaurante(
                donoRestauranteEntity.getId(),
                donoRestauranteEntity.getNome(),
                new Endereco(donoRestauranteEntity.getEndereco()),
                donoRestauranteEntity.getEmail(),
                donoRestauranteEntity.getLogin(),
                donoRestauranteEntity.getSenha(),
                donoRestauranteEntity.getDataUltimaAlteracao()
        );
    }
}
