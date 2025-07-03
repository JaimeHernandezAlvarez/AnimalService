package com.gestion.animales;

import com.gestion.animales.model.Animal;
import com.gestion.animales.model.Propietario;
import com.gestion.animales.repository.PropietarioRepository;
import com.gestion.animales.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GestionDeAnimalesApplicationTests {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Test
    void contextLoads() {
        assertNotNull(animalService); // Verifica que se inyectó el servicio
    }

    @Test
    void contextoSimple() {
        System.out.println("Contexto cargado correctamente");
    }

    @Test
    void testGuardarAnimal() {
        // Crear y guardar propietario real
        Propietario propietario = new Propietario();
        propietario.setNombre("Juan Test");
        propietario.setTelefono("123456789");
        propietario.setDireccion("Calle Falsa 123");
        propietario = propietarioRepository.save(propietario); // se guarda y ya tiene ID

        // Crear el animal con ese propietario
        Animal animal = new Animal();
        animal.setNombre("Testy");
        animal.setEspecie("Testeraptor");
        animal.setGenero("M");
        animal.setEstado("Sano");
        animal.setPropietario(propietario); // asignar objeto

        Animal guardado = animalService.save(animal);

        // Aserciones
        assertNotNull(guardado.getId());
        assertEquals("Testy", guardado.getNombre());
        assertEquals("Testeraptor", guardado.getEspecie());
        assertEquals(propietario.getId(), guardado.getPropietario().getId());
    }
}
