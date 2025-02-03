package com.espe.equipos.repositories;

import com.espe.equipos.model.entity.Equipo;
import org.springframework.data.repository.CrudRepository;

public interface EquipoRepository extends CrudRepository<Equipo, Long> {
    // Puedes agregar métodos personalizados si lo necesitas, por ejemplo:
    // Optional<Equipo> findByNombre(String nombre);
}
