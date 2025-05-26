package com.example.escuela.controller;

import com.example.dto.AlumnoDTO;
import com.example.escuela.model.Alumno;
import com.example.escuela.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con los alumnos.
 * Permite crear, listar, obtener, actualizar y eliminar alumnos.
 * 
 * Anotaciones:
 * - @RestController: Indica que esta clase es un controlador REST.
 * - @CrossOrigin: Permite solicitudes de origen cruzado desde cualquier origen.
 * - @RequestMapping: Define la ruta base para todas las operaciones de este
 * controlador.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/alumnos")
public class AlumnoController {

    /**
     * Servicio para manejar la lógica de negocio relacionada con los alumnos.
     */
    @Autowired
    private AlumnoService alumnoService;

    /**
     * Constructor por defecto para la clase AlumnoController.
     */
    public AlumnoController() {
    }

    /**
     * Crea un nuevo alumno con los datos proporcionados en el DTO.
     * 
     * @param alumnoDTO Objeto que contiene los datos del alumno a crear.
     * @return El alumno creado.
     */
    @PostMapping
    public Alumno crearAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.crearAlumno(alumnoDTO);
    }

    /**
     * Lista todos los alumnos.
     * 
     * @return Lista de alumnos.
     */
    @GetMapping
    public List<Alumno> listarAlumnos() {
        return alumnoService.listarAlumnos();
    }

    /**
     * Obtiene un alumno por su ID.
     * 
     * @param id Identificador del alumno a obtener.
     * @return El alumno correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public Alumno obtenerAlumno(@PathVariable Integer id) {
        return alumnoService.obtenerAlumnoPorId(id);
    }

    /**
     * Actualiza un alumno existente buscándolo por su ID.
     * 
     * @param id        Identificador del alumno a actualizar.
     * @param alumnoDTO Objeto que contiene los nuevos datos del alumno.
     * @return El alumno actualizado.
     */
    @PutMapping("/{id}")
    public Alumno actualizarAlumno(@PathVariable Integer id, @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.actualizarAlumno(id, alumnoDTO);
    }

    /**
     * Elimina un alumno por su ID.
     * 
     * @param id Identificador del alumno a eliminar.
     * @return El alumno eliminado.
     */
    @DeleteMapping("/{id}")
    public Alumno eliminarAlumno(@PathVariable Integer id) {
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id);
        alumnoService.eliminarAlumno(id);

        return alumno;
    }
}
