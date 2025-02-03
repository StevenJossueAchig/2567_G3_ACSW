package com.espe.torneos.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "torneos_equipos")
public class TorneoEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipo_id", unique = false)
    private Long equipoId;

    @Column(name = "torneo_id", unique = false)
    private Long torneoId;

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para equipoId
    public Long getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Long equipoId) {
        this.equipoId = equipoId;
    }

    // Getter y Setter para torneoId
    public Long getTorneoId() {
        return torneoId;
    }

    public void setTorneoId(Long torneoId) {
        this.torneoId = torneoId;
    }

    // Modificación del método equals para comparar correctamente los objetos
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TorneoEquipo)) {
            return false;
        }
        TorneoEquipo o = (TorneoEquipo) obj;
        return this.equipoId != null && this.equipoId.equals(o.equipoId) && this.torneoId != null && this.torneoId.equals(o.torneoId);
    }

    //@Override
    //public int hashCode() {
    //    return Objects.hash(id, equipoId, torneoId);
    //}
}
