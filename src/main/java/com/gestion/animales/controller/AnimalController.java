package com.gestion.animales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.animales.model.Animal;
import com.gestion.animales.service.AnimalService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/animales")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animal>> listar(){
        List<Animal> animales = animalService.findAll();
        if(animales.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animales);
    }

    @PostMapping
    public ResponseEntity<Animal> guardar(@RequestBody Animal animal){
        Animal nuevo = animalService.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscar(@PathVariable Integer id){
        try{
            Animal animal = animalService.findById(id);
            return ResponseEntity.ok(animal);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Animal> actualizar(@PathVariable Integer id, @RequestBody Animal animal){
        try{
            Animal ani = animalService.findById(id);
            ani.setId(id);
            ani.setNombre(animal.getNombre());
            ani.setEspecie(animal.getEspecie());
            ani.setEstado(animal.getEstado());
            ani.setGenero(animal.getGenero());

            animalService.save(ani);
            return ResponseEntity.ok(animal);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try{
            animalService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}

//jAIME