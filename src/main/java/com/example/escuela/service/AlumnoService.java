package com.example.escuela.service;

import com.example.dto.AlumnoDTO;
import com.example.escuela.model.Alumno;

import java.util.List;

/**
 * Interfaz del servicio AlumnoService.
 * Proporciona métodos para crear, actualizar, eliminar y obtener información de alumnos.
 * Utiliza el modelo Alumno y el DTO AlumnoDTO para las operaciones.
 */
public interface AlumnoService {
    /**
     * Crea un nuevo alumno a partir de un AlumnoDTO.
     *
     * @param alumnoDTO el DTO que contiene los datos del alumno a crear
     * @return el objeto Alumno creado
     */
    Alumno crearAlumno(AlumnoDTO alumnoDTO);

    /**
     * Actualiza un alumno existente.
     *
     * @param id el identificador del alumno a actualizar
     * @param alumnoDTO el DTO que contiene los nuevos datos del alumno
     * @return el objeto Alumno actualizado
     */
    Alumno actualizarAlumno(Integer id, AlumnoDTO alumnoDTO);

    /**
     * Elimina un alumno por su identificador.
     *
     * @param id el identificador del alumno a eliminar
     */
    void eliminarAlumno(Integer id);

    /**
     * Obtiene un alumno por su identificador.
     *
     * @param id el identificador del alumno a obtener
     * @return el objeto Alumno correspondiente al identificador
     */
    Alumno obtenerAlumnoPorId(Integer id);

    /**
     * Lista todos los alumnos.
     *
     * @return una lista de objetos Alumno
     */
    List<Alumno> listarAlumnos();
}
