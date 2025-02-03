package com.espe.equipos.services;

import com.espe.equipos.model.entity.Equipo;
import com.espe.equipos.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<Equipo> findAll() {
        return (List<Equipo>) equipoRepository.findAll();
    }

    @Override
    public Optional<Equipo> findById(Long id) {
        return equipoRepository.findById(id);
    }

    @Override
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public void deleteById(Long id) {
        equipoRepository.deleteById(id);
    }
}
