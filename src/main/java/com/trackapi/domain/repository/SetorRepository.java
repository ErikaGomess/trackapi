package com.trackapi.domain.repository;

import com.trackapi.domain.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
    Setor findByNome(String nome);
}
