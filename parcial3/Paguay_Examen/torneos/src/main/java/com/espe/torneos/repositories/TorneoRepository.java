package com.espe.torneos.repositories;

import com.espe.torneos.model.Equipo;
import com.espe.torneos.model.entity.Torneo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TorneoRepository extends CrudRepository<Torneo, Long> {
    List<Equipo> findEquiposById(Long torneoId);

    List<Torneo> findTorneosById(Long equipoId);
    // Aquí puedes agregar métodos adicionales si es necesario, por ejemplo:
    // List<Torneo> findByNombre(String nombre);
}
