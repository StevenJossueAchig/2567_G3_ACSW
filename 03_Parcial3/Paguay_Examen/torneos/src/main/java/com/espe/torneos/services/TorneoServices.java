package com.espe.torneos.services;

import com.espe.torneos.model.Equipo;
import com.espe.torneos.model.entity.Torneo;
import com.espe.torneos.model.entity.TorneoEquipo;

import java.util.List;
import java.util.Optional;

public interface TorneoServices {
    // Obtener todos los torneos
    List<Torneo> findAll();

    // Obtener torneo por ID
    Optional<Torneo> findById(Long id);

    // Guardar un torneo
    Torneo save(Torneo torneo);

    // Eliminar un torneo por ID
    void deleteById(Long id);

    // Asignar un equipo a un torneo
    Optional<TorneoEquipo> asignarEquipo(Equipo equipo, Long id);

    // Obtener equipos de un torneo
    List<Equipo> obtenerEquipos(Long torneoId);

    List<Equipo> listarEquiposPorTorneo(Long torneoId);

    // Listar los torneos en los que un equipo ha participado
    List<Torneo> listarTorneosPorEquipo(Long equipoId);

    // Cancelar la participación de un equipo en un torneo
    void cancelarParticipacion(Long torneoId, Long equipoId);
}
