package com.gestion.animales;

import com.gestion.animales.model.Animal;
import com.gestion.animales.repository.AnimalRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Animal animal = new Animal();
            animal.setNombre(faker.name().firstName());
            animal.setEspecie(faker.animal().name());
            animal.setGenero(random.nextBoolean() ? "M" : "F");
            animal.setEstado(faker.medical().symptoms());
            animal.setPropietario((long) faker.number().numberBetween(1, 100)); // solo como número

            animalRepository.save(animal);
        }
    }
}
