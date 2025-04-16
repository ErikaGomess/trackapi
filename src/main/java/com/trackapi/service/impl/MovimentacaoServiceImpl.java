package com.trackapi.service.impl;


import com.trackapi.domain.model.Movimentacao;
import com.trackapi.domain.repository.MovimentacaoRepository;
import com.trackapi.service.MovimentacaoService;
import com.trackapi.service.exception.BusinessException;
import com.trackapi.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static java.util.Optional.ofNullable;
import java.util.List;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoServiceImpl(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Transactional (readOnly = true)
    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Movimentacao findById(Long id) {
        return movimentacaoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Movimentacao create(Movimentacao entity) {
        ofNullable(entity).orElseThrow(() -> new BusinessException("Movimentação não pode ser nula."));
        return movimentacaoRepository.save(entity);
    }

    @Transactional
    public Movimentacao update(Long id, Movimentacao entity) {
        Movimentacao existente = this.findById(id);
        if (!existente.getId().equals(entity.getId())) {
            throw new BusinessException("IDs da movimentação não coincidem.");
        }

        existente.setDataHora(entity.getDataHora());
        existente.setDeSetor(entity.getDeSetor());
        existente.setParaSetor(entity.getParaSetor());
        existente.setObservacao(entity.getObservacao());
        existente.setEncomenda(entity.getEncomenda());

        return movimentacaoRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        Movimentacao existente = this.findById(id);
        movimentacaoRepository.delete(existente);
    }
}