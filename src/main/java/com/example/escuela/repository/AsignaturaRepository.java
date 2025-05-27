package com.example.escuela.repository;

import com.example.escuela.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad Asignatura.
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "asignaturas".
 * 
 * Anotaciones:
 * - @Repository: Indica que esta interfaz es un repositorio de Spring Data JPA.
 * - @JpaRepository: Proporciona métodos predefinidos para operaciones CRUD y consultas personalizadas.
 */
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

    /**
     * Encuentra una lista de asignaturas asociadas a un grado específico.
     *
     * @param idGrado el identificador del grado
     * @return una lista de asignaturas que pertenecen al grado especificado
     */
    List<Asignatura> findByGradoId(Integer idGrado);
}
