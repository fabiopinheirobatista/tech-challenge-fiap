package br.com.techchallenge.infra.repository;

import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensCardapioRepository extends JpaRepository<ItensCardapioEntity, Long> {}