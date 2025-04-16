package com.trackapi.domain.repository;



import com.trackapi.domain.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
    // Exemplo: buscar por código de rastreamento
    Encomenda findByCodigoRastreamento(String codigoRastreamento);
}
