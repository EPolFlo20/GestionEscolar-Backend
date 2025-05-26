package com.example.escuela.model;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Clase que representa un alumno en el sistema escolar.
 * Contiene información básica del alumno como matrícula, nombre, correo electrónico y el grado al que pertenece.
 * 
 * Anotaciones:
 * - @Entity: Indica que esta clase es una entidad JPA.
 * - @Table: Especifica la tabla de la base de datos a la que está mapeada esta entidad.
 * - @Data: Genera automáticamente los métodos getter, setter, toString, equals y hashCode.
 */
@Entity
@Table(name = "asignaturas")
@Data
public class Asignatura {

    /**
     * Constructor por defecto para la clase Asignatura.
     */
    public Asignatura() {
    }

    /**
     * Identificador único de la asignatura.
     * Se genera automáticamente al insertar un nuevo registro en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private Integer id;

    /**
     * Nombre de la asignatura.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Identificador del grado al que pertenece la asignatura.
     */
    @ManyToOne
    @JoinColumn(name = "id_grado")
    private Grado grado;
}

