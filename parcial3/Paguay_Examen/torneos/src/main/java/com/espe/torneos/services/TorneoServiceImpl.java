package com.espe.torneos.services;

import com.espe.torneos.clients.EquipoClientRest;
import com.espe.torneos.model.Equipo;
import com.espe.torneos.model.entity.Torneo;
import com.espe.torneos.model.entity.TorneoEquipo;
import com.espe.torneos.repositories.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TorneoServiceImpl implements TorneoServices {

    @Autowired
    private TorneoRepository torneoRepository;

    @Autowired
    private EquipoClientRest equipoClientRest;

    @Override
    public List<Torneo> findAll() {
        return (List<Torneo>) torneoRepository.findAll();
    }

    @Override
    public Optional<Torneo> findById(Long id) {
        return torneoRepository.findById(id);
    }

    @Override
    public Torneo save(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    @Override
    public void deleteById(Long id) {
        torneoRepository.deleteById(id);
    }

    @Override
    public Optional<TorneoEquipo> asignarEquipo(Equipo equipo, Long id) {
        // Buscar el torneo por su ID
        Optional<Torneo> optional = torneoRepository.findById(id);
        System.out.println(optional.get().getNombre());

        // Verifica si el torneo existe
        if (optional.isPresent()) {
            // Busca el equipo por su ID usando el cliente REST
            Equipo equipoTemp = equipoClientRest.findById(equipo.getId());
            System.out.println( equipoTemp.getNombre());
            if (equipoTemp != null) {
                // Obtiene el torneo desde el Optional
                Torneo torneo = optional.get();

                // Crea una nueva relación entre el torneo y el equipo
                TorneoEquipo torneoEquipoRel = new TorneoEquipo();
                torneoEquipoRel.setEquipoId(equipoTemp.getId());


                // Agrega la relación al torneo (asumiendo que en la entidad Torneo ya tienes una colección de TorneoEquipo)
                torneo.addTorneoEquipo(torneoEquipoRel);

                // Guarda los cambios en el torneo
                torneoRepository.save(torneo);

                // Retorna la relación TorneoEquipo creada
                return Optional.of(torneoEquipoRel);
            }
        }

        // Si el torneo o el equipo no se encuentran, retorna un Optional vacío
        return Optional.empty();
    }

    @Override
    public List<Equipo> obtenerEquipos(Long torneoId) {
        return List.of();
    }


    @Override
    public List<Equipo> listarEquiposPorTorneo(Long torneoId) {
        // Buscar el torneo por su ID
        Optional<Torneo> optionalTorneo = torneoRepository.findById(torneoId);

        // Verificar si el torneo existe
        if (optionalTorneo.isPresent()) {
            Torneo torneo = optionalTorneo.get();

            // Retornar la lista de equipos a partir de las relaciones TorneoEquipo
            return torneo.getTorneoEquipos().stream()
                    .map(torneoEquipo -> equipoClientRest.findById(torneoEquipo.getEquipoId()))
                    .collect(Collectors.toList());
        }

        // Si el torneo no se encuentra, retornar una lista vacía
        return List.of();
    }


    @Override
    public List<Torneo> listarTorneosPorEquipo(Long equipoId) {
        // Aquí se puede hacer una consulta para listar los torneos en los que un equipo ha participado
        return torneoRepository.findTorneosById(equipoId);
    }

    @Override
    public void cancelarParticipacion(Long torneoId, Long equipoId) {
        // Buscar el torneo por su ID
        Optional<Torneo> optional = torneoRepository.findById(torneoId);
        if (optional.isPresent()) {
            Torneo torneo = optional.get();
            // Buscar la relación entre el torneo y el equipo
            Optional<TorneoEquipo> torneoEquipoOptional = torneo.getTorneoEquipos().stream()
                    .filter(torneoEquipo -> torneoEquipo.getEquipoId().equals(equipoId))
                    .findFirst();
            if (torneoEquipoOptional.isPresent()) {
                // Eliminar la relación entre el torneo y el equipo
                torneo.removeTorneoEquipo(torneoEquipoOptional.get()); // Implementa el método removeTorneoEquipo en la clase Torneo
                torneoRepository.save(torneo); // Guardar los cambios en el torneo
            }
        }
    }

}
