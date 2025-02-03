package com.espe.torneos.clients;

import com.espe.torneos.model.Equipo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-equipos", url = "localhost:8002/api/equipos")  // Ajusta la URL según el servicio de equipos
public interface EquipoClientRest {

    @GetMapping("/{id}")
    Equipo findById(@PathVariable Long id);
}
