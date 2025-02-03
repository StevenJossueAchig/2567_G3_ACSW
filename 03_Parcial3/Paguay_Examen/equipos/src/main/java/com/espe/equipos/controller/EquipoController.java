package com.espe.equipos.controller;

import com.espe.equipos.model.entity.Equipo;
import com.espe.equipos.services.EquipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "http://localhost:3000")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Equipo equipo, BindingResult result) {
        if (validar(result) != null) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.save(equipo));
    }

    @GetMapping
    public List<Equipo> listar() {
        return equipoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Equipo> equipoOptional = equipoService.findById(id);
        if (equipoOptional.isPresent()) {
            return ResponseEntity.ok().body(equipoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Equipo equipo, @PathVariable Long id) {
        Optional<Equipo> equipoOptional = equipoService.findById(id);
        if (equipoOptional.isPresent()) {
            Equipo equipoDB = equipoOptional.get();
            equipoDB.setNombre(equipo.getNombre());
            equipoDB.setDescripcion(equipo.getDescripcion());

            return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.save(equipoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Equipo> equipoOptional = equipoService.findById(id);
        if (equipoOptional.isPresent()) {
            equipoService.deleteById(id);
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
}
