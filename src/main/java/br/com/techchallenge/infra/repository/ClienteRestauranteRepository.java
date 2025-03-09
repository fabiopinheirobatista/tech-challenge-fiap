package br.com.techchallenge.infra.repository;

import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRestauranteRepository extends JpaRepository<ClienteRestauranteEntity, Long> {

    boolean existsByEmail(String email);
}