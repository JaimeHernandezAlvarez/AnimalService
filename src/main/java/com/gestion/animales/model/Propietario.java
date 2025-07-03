package com.gestion.animales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "propietario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propietario_seq")
    @SequenceGenerator(name = "propietario_seq", sequenceName = "PROPIETARIO_SEQ", allocationSize = 1)
    @Column(name = "id_propietario")
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion", length = 100)
    private String direccion;
}
