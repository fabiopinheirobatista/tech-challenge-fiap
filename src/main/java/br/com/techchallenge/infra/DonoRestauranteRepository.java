package br.com.techchallenge.infra;

import br.com.techchallenge.domain.DonoRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoRestauranteRepository extends JpaRepository<DonoRestaurante, Long> {
}