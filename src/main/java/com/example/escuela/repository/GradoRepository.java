package com.example.escuela.repository;

import com.example.escuela.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Grado.
 * Proporciona métodos para realizar operaciones CRUD sobre la tabla "grados".
 *
 * Anotaciones:
 * @Repository: Indica que esta interfaz es un repositorio de Spring Data JPA.
 * @JpaRepository: Proporciona métodos predefinidos para operaciones CRUD y consultas personalizadas.
 */
public interface GradoRepository extends JpaRepository<Grado, Integer> {
}
