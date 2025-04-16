package com.trackapi.service.impl;

import com.trackapi.domain.model.Encomenda;
import com.trackapi.domain.repository.EncomendaRepository;
import com.trackapi.service.EncomendaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.trackapi.service.exception.NotFoundException;
import com.trackapi.service.exception.BusinessException;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class EncomendaServiceImpl implements EncomendaService {

    private final EncomendaRepository encomendaRepository;

    public EncomendaServiceImpl(EncomendaRepository encomendaRepository) {
        this.encomendaRepository = encomendaRepository;
    }

    @Transactional(readOnly = true)
    public List<Encomenda> findAll() {
        return encomendaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Encomenda findById(Long id) {
        return encomendaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Encomenda create(Encomenda entity) {
        ofNullable(entity).orElseThrow(() -> new BusinessException("Encomenda a ser criada não pode ser nula."));
        ofNullable(entity.getCodigoRastreamento()).orElseThrow(() -> new BusinessException("Código de rastreamento é obrigatório."));
        return encomendaRepository.save(entity);
    }

    @Transactional
    public Encomenda update(Long id, Encomenda entity) {
        Encomenda existente = this.findById(id);
        if (!existente.getId().equals(entity.getId())) {
            throw new BusinessException("IDs da encomenda não coincidem.");
        }

        existente.setDescricao(entity.getDescricao());
        existente.setStatus(entity.getStatus());
        existente.setLocalAtual(entity.getLocalAtual());
        existente.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        existente.setMovimentacoes(entity.getMovimentacoes());

        return encomendaRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        Encomenda existente = this.findById(id);
        encomendaRepository.delete(existente);
    }
}
