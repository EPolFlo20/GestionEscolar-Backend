package com.example.escuela.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Clase que representa una calificación en el sistema escolar.
 * Contiene información sobre el alumno, la asignatura y la calificación obtenida.
 * 
 * Anotaciones:
 * - @Entity: Indica que esta clase es una entidad JPA.
 * - @Table: Especifica la tabla de la base de datos a la que está mapeada esta entidad.
 * - @Data: Genera automáticamente los métodos getter, setter, toString, equals y hashCode.
 */
@Entity
@Table(name = "grados")
@Data
public class Grado {

    /**
     * Constructor por defecto para la clase Grado.
     */
    public Grado() {
    }

    /**
     * Identificador único del grado.
     * Se genera automáticamente al insertar un nuevo registro en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado")
    private Integer id;

    /**
     * Nombre del grado.
     */
    @Column(name = "nombre_grado")
    private String nombre_grado;
}
