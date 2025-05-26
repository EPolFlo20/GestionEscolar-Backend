package com.example.escuela.repository;

import com.example.escuela.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad Calificacion.
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "calificaciones".
 *
 * Anotaciones:
 * @Repository: Indica que esta interfaz es un repositorio de Spring Data JPA.
 * @JpaRepository: Proporciona métodos predefinidos para operaciones CRUD y consultas personalizadas.
 */
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {

    /**
     * Encuentra una lista de calificaciones asociadas a un alumno específico.
     *
     * @param idAlumno el identificador del alumno
     * @return una lista de calificaciones que pertenecen al alumno especificado
     */
    List<Calificacion> findByAlumnoId(Integer idAlumno);

    /**
     * Encuentra una lista de calificaciones asociadas a una asignatura específica.
     *
     * @param idAsignatura el identificador de la asignatura
     * @return una lista de calificaciones que pertenecen a la asignatura especificada
     */
    List<Calificacion> findByAsignaturaId(Integer idAsignatura);
}
