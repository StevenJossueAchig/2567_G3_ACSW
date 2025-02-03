package com.espe.torneos.controller;

import com.espe.torneos.model.Equipo;
import com.espe.torneos.model.entity.Torneo;
import com.espe.torneos.model.entity.TorneoEquipo;
import com.espe.torneos.services.TorneoServices;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    @Autowired
    private TorneoServices torneoServices;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Torneo torneo, BindingResult result) {
        if (validar(result) != null) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoServices.save(torneo));
    }

    @GetMapping
    public List<Torneo> listar() {
        return torneoServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@Valid @PathVariable Long id) {
        Optional<Torneo> torneoOptional = torneoServices.findById(id);
        if (torneoOptional.isPresent()) {
            return ResponseEntity.ok().body(torneoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Torneo torneo, @PathVariable Long id) {
        Optional<Torneo> torneoOptional = torneoServices.findById(id);
        if (torneoOptional.isPresent()) {
            Torneo torneoDB = torneoOptional.get();
            torneoDB.setNombre(torneo.getNombre());
            torneoDB.setDescripcion(torneo.getDescripcion());
            return ResponseEntity.status(HttpStatus.CREATED).body(torneoServices.save(torneoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Torneo> torneoOptional = torneoServices.findById(id);
        if (torneoOptional.isPresent()) {
            torneoServices.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> validar(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(
                    err -> errores.put(
                            err.getField(), err.getDefaultMessage()
                    )
            );
            return ResponseEntity.badRequest().body(errores);
        }
        return null;
    }

    @PutMapping("/asignar-equipo/{torneoId}")
    public ResponseEntity<?> asignarEquipo(@RequestBody Equipo equipo, @PathVariable Long torneoId) {
        Optional<TorneoEquipo> o;
        try {
            o = torneoServices.asignarEquipo(equipo, torneoId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el torneo con el ID " + torneoId + " o error de comunicación: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/equipos/{torneoId}")
    public ResponseEntity<List<Equipo>> listarEquiposPorTorneo(@PathVariable Long torneoId) {
        List<Equipo> equipos = torneoServices.listarEquiposPorTorneo(torneoId);
        return ResponseEntity.ok(equipos);
    }

    @DeleteMapping("/cancelar-participacion/{torneoId}/{equipoId}")
    public ResponseEntity<?> cancelarParticipacion(@PathVariable Long torneoId, @PathVariable Long equipoId) {
        torneoServices.cancelarParticipacion(torneoId, equipoId);
        return ResponseEntity.noContent().build();
    }
}
