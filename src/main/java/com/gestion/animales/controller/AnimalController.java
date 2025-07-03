package com.gestion.animales.controller;

import com.gestion.animales.model.Animal;
import com.gestion.animales.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animales")
@Tag(name = "Animal Controller", description = "Controlador básico sin HATEOAS para animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Operation(summary = "Listar todos los animales", description = "Devuelve una lista de todos los animales")
    @GetMapping
    public ResponseEntity<List<Animal>> listar() {
        List<Animal> animales = animalService.findAll();
        if (animales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animales);
    }

    @Operation(summary = "Guardar nuevo animal", description = "Crea un nuevo animal en la base de datos")
    @PostMapping
    public ResponseEntity<Animal> guardar(@RequestBody Animal animal) {
        Animal nuevo = animalService.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @Operation(summary = "Buscar animal por ID", description = "Devuelve los datos de un animal según su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscar(@PathVariable Integer id) {
        try {
            Animal animal = animalService.findById(id);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Actualizar un animal", description = "Modifica los datos de un animal existente")
    @PutMapping("/{id}")
    public ResponseEntity<Animal> actualizar(@PathVariable Integer id, @RequestBody Animal animal) {
        try {
            Animal ani = animalService.findById(id);
            ani.setId(id);
            ani.setNombre(animal.getNombre());
            ani.setEspecie(animal.getEspecie());
            ani.setEstado(animal.getEstado());
            ani.setGenero(animal.getGenero());

            animalService.save(ani);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar animal", description = "Elimina un animal de la base de datos por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            animalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
