package com.trackapi.domain.repository;


import com.trackapi.domain.model.Encomenda;
import com.trackapi.domain.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByEncomenda(Encomenda encomenda);
}
