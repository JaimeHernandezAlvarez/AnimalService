package com.gestion.animales.service;

import com.gestion.animales.model.Propietario;
import com.gestion.animales.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    public List<Propietario> findAll() {
        return propietarioRepository.findAll();
    }

    public Propietario save(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }

    public Propietario findById(Long id) {
        return propietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
    }
}
