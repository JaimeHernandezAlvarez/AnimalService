package com.gestion.animales;

import com.gestion.animales.model.Animal;
import com.gestion.animales.model.Propietario;
import com.gestion.animales.repository.AnimalRepository;
import com.gestion.animales.repository.PropietarioRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();

        // Crea 10 propietarios reales
        for (int i = 0; i < 10; i++) {
            Propietario p = new Propietario();
            p.setNombre(faker.name().fullName());
            p.setTelefono(faker.phoneNumber().cellPhone());
            p.setDireccion(faker.address().fullAddress());
            propietarioRepository.save(p);
        }

        List<Propietario> propietarios = propietarioRepository.findAll();

        // Crea 20 animales con propietarios reales
        for (int i = 0; i < 20; i++) {
            Animal animal = new Animal();
            animal.setNombre(faker.name().firstName());
            animal.setEspecie(faker.animal().name());
            animal.setGenero(random.nextBoolean() ? "M" : "F");
            animal.setEstado(faker.medical().symptoms());
            animal.setPropietario(propietarios.get(random.nextInt(propietarios.size()))); // ✅ se pasa el objeto Propietario
            animalRepository.save(animal);
        }
    }
}
