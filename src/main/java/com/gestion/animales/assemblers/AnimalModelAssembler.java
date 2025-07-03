package com.gestion.animales.assemblers;

import com.gestion.animales.controller.AnimalControllerV2;
import com.gestion.animales.model.Animal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AnimalModelAssembler implements RepresentationModelAssembler<Animal, EntityModel<Animal>> {

    @Override
    public EntityModel<Animal> toModel(Animal animal) {
        return EntityModel.of(animal,
            linkTo(methodOn(AnimalControllerV2.class).getAnimalById(animal.getId())).withSelfRel(),
            linkTo(methodOn(AnimalControllerV2.class).getAllAnimals()).withRel("animales")
        );
    }
}
