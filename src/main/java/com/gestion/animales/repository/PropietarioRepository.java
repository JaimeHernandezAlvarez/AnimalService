package com.gestion.animales.repository;

import com.gestion.animales.model.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropietarioRepository extends JpaRepository<Propietario, Long> {
}
