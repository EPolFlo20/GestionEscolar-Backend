package com.example.escuela.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Clase que representa una calificación en el sistema escolar.
 * Contiene información sobre el alumno, la asignatura y la calificación obtenida.
 * 
 * Anotaciones:
 * @Entity: Indica que esta clase es una entidad JPA.
 * @Table: Especifica la tabla de la base de datos a la que está mapeada esta entidad.
 * @Data: Genera automáticamente los métodos getter, setter, toString, equals y hashCode.
 */
@Entity
@Table(name = "calificaciones")
@Data
public class Calificacion {

    /**
     * Identificador único de la calificación.
     * Se genera automáticamente al insertar un nuevo registro en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Integer id;

    /**
     * Relación ManyToOne con la entidad Alumno.
     * Indica que una calificación pertenece a un único alumno.
     */
    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    /**
     * Relación ManyToOne con la entidad Asignatura.
     * Indica que una calificación corresponde a una única asignatura.
     */
    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    /**
     * Calificación obtenida por el alumno en la asignatura.
     * Es un campo obligatorio.
     */
    @Column(name = "calificacion")
    private Float calificacion;
}
