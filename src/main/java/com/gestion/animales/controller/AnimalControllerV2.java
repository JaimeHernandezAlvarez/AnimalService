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
}
