package com.espe.torneos.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Torneos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre"})
})
public class Torneo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String fechaInicio;

    @Column(nullable = false)
    private String fechaFin;

    // Relación con TorneoEquipo
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "torneo_id")
    private List<TorneoEquipo> torneoEquipos;

    // Constructor vacío
    public Torneo() {
        torneoEquipos = new ArrayList<>();
    }

    // Métodos para agregar y eliminar equipos del torneo
    public void addTorneoEquipo(TorneoEquipo torneoEquipo) {
        torneoEquipos.add(torneoEquipo);
    }

    public void removeTorneoEquipo(TorneoEquipo torneoEquipo) {
        torneoEquipos.remove(torneoEquipo);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<TorneoEquipo> getTorneoEquipos() {
        return torneoEquipos;
    }

    public void setTorneoEquipos(List<TorneoEquipo> torneoEquipos) {
        this.torneoEquipos = torneoEquipos;
    }
}
