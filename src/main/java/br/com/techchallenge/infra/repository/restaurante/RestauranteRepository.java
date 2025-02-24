package br.com.techchallenge.infra.repository.restaurante;

import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}
