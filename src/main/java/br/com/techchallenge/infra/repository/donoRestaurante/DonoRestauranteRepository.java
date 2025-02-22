package br.com.techchallenge.infra.repository.donoRestaurante;

import br.com.techchallenge.domain.DonoRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoRestauranteRepository extends JpaRepository<DonoRestaurante, Long> {
}
