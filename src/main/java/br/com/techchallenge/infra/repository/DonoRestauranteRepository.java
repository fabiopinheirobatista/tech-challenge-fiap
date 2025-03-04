package br.com.techchallenge.infra.repository;

import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoRestauranteRepository extends JpaRepository<DonoRestauranteEntity, Long> {
    boolean existsByEmailOrLogin(String email, String login);
}
