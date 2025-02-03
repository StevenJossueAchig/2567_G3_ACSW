package com.espe.equipos.services;

import com.espe.equipos.model.entity.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    List<Equipo> findAll();
    Optional<Equipo> findById(Long id);
    Equipo save(Equipo equipo);
    void deleteById(Long id);
}
