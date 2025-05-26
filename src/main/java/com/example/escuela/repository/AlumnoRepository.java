package com.example.escuela.repository;

import com.example.escuela.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Alumno.
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "alumnos".
 * 
 * Anotaciones:
 * @Repository: Indica que esta interfaz es un repositorio de Spring Data JPA.
 * @JpaRepository: Proporciona métodos predefinidos para operaciones CRUD y consultas personalizadas.
 */
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
}
