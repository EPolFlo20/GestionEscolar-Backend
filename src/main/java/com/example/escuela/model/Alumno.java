package com.example.escuela.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Clase que representa un alumno en el sistema escolar.
 * Contiene información básica del alumno como matrícula, nombre, correo
 * electrónico y el grado al que pertenece.
 * 
 */
@Entity
@Table(name = "alumnos")
@Data
public class Alumno {

    /**
     * Constructor por defecto para la clase Alumno.
     */
    public Alumno() {
    }

    /**
     * Identificador único del alumno.
     * Se genera automáticamente al insertar un nuevo registro en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Integer id;

    /**
     * Matrícula del alumno.
     */
    @Column(name = "matricula")
    private String matricula;

    /**
     * Nombre del alumno.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Correo electrónico del alumno.
     */
    @Column(name = "correo_electronico")
    private String correoElectronico;

    /**
     * Grado al que pertenece el alumno.
     * Es una relación ManyToOne con la entidad Grado, indicando que un grado puede
     * tener varios alumnos.
     */
    @ManyToOne
    @JoinColumn(name = "id_grado")
    private Grado grado;
}