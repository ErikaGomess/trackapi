package com.trackapi.service.impl;



import com.trackapi.domain.model.Setor;
import com.trackapi.domain.repository.SetorRepository;
import com.trackapi.service.SetorService;
import com.trackapi.service.exception.BusinessException;
import com.trackapi.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Transactional(readOnly = true)
    public List<Setor> findAll() {
        return setorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Setor findById(Long id) {
        return setorRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Setor create(Setor entity) {
        ofNullable(entity).orElseThrow(() -> new BusinessException("Setor não pode ser nulo."));
        return setorRepository.save(entity);
    }

    @Transactional
    public Setor update(Long id, Setor entity) {
        Setor existente = this.findById(id);
        if (!existente.getId().equals(entity.getId())) {
            throw new BusinessException("IDs do setor não coincidem.");
        }

        existente.setNome(entity.getNome());
        existente.setResponsavel(entity.getResponsavel());

        return setorRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        Setor existente = this.findById(id);
        setorRepository.delete(existente);
    }
}
