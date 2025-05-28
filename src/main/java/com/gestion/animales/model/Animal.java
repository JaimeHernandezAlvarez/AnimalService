package com.gestion.animales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="animal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Integer id;

    @Column(name = "nombre_animal",length = 15)
    private String nombre;

    @Column(name = "especie",length = 15)
    private String especie;

    @Column(name = "genero",length = 1)
    private String genero;

    @Column(name = "estado_animal",length = 200)
    private String estado;

    @JoinColumn(name = "usuario_id")
    private Long usuarioId;
}
