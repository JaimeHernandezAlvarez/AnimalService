package com.gestion.animales.controller;

import com.gestion.animales.assemblers.AnimalModelAssembler;
import com.gestion.animales.model.Animal;
import com.gestion.animales.service.AnimalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/animales")
@Tag(name = "Animal Controller V2", description = "Controlador REST con soporte HATEOAS para animales")
public class AnimalControllerV2 {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalModelAssembler assembler;

    @Operation(summary = "Obtener un animal por su ID", description = "Retorna un animal con enlaces")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Animal>> getAnimalById(@PathVariable Integer id) {
        try {
            Animal animal = animalService.findById(id);
            return ResponseEntity.ok(assembler.toModel(animal));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todos los animales", description = "Retorna todos los animales con enlaces")
    @GetMapping
    public CollectionModel<EntityModel<Animal>> getAllAnimals() {
        List<EntityModel<Animal>> animales = animalService.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(animales,
            linkTo(methodOn(AnimalControllerV2.class).getAllAnimals()).withSelfRel());
    }

    @Operation(summary = "Guardar nuevo animal", description = "Crea un nuevo animal y lo retorna con enlaces HATEOAS")
    @PostMapping
    public ResponseEntity<EntityModel<Animal>> guardar(@RequestBody Animal animal) {
        Animal nuevo = animalService.save(animal);
        return ResponseEntity
                .created(linkTo(methodOn(AnimalControllerV2.class).getAnimalById(nuevo.getId())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @Operation(summary = "Actualizar un animal", description = "Actualiza los datos de un animal existente con enlaces HATEOAS")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Animal>> actualizar(@PathVariable Integer id, @RequestBody Animal animal) {
        try {
            Animal existente = animalService.findById(id);
            existente.setNombre(animal.getNombre());
            existente.setEspecie(animal.getEspecie());
            existente.setGenero(animal.getGenero());
            existente.setEstado(animal.getEstado());
            existente.setPropietario(animal.getPropietario());

            Animal actualizado = animalService.save(existente);
            return ResponseEntity.ok(assembler.toModel(actualizado));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar animal", description = "Elimina un animal de la base de datos por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            animalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
