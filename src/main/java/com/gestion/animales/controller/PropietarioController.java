package com.gestion.animales.controller;

import com.gestion.animales.model.Propietario;
import com.gestion.animales.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/propietarios")
public class PropietarioController {

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping
    public ResponseEntity<List<Propietario>> listar() {
        return ResponseEntity.ok(propietarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<Propietario> guardar(@RequestBody Propietario p) {
        return ResponseEntity.ok(propietarioService.save(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propietario> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(propietarioService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
