package br.com.techchallenge.infra.repository;

import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonoRestauranteRepository extends JpaRepository<DonoRestauranteEntity, Long> {
    Optional<DonoRestauranteEntity> findByEmailAndLogin(String email, String login);
}
