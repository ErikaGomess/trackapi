package com.trackapi.service.impl;

import com.trackapi.domain.model.Usuario;
import com.trackapi.domain.repository.UsuarioRepository;
import com.trackapi.service.UsuarioService;
import com.trackapi.service.exception.BusinessException;
import com.trackapi.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Usuario create(Usuario entity) {
        ofNullable(entity).orElseThrow(() -> new BusinessException("Usuario não pode ser nulo."));
        return usuarioRepository.save(entity);
    }

    @Transactional
    public Usuario update(Long id, Usuario entity) {
        Usuario existente = this.findById(id);
        if (!existente.getId().equals(entity.getId())) {
            throw new BusinessException("IDs do usuario não coincidem.");
        }

        existente.setNome(entity.getNome());
        existente.setEmail(entity.getEmail());
        existente.setSenha(entity.getSenha());
        existente.setSetor(entity.getSetor());
        existente.setRole(entity.getRole());

        return usuarioRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        Usuario existente = this.findById(id);
        usuarioRepository.delete(existente);
    }
}
