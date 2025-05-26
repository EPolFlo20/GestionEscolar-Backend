package com.example.escuela.controller;

import com.example.dto.AlumnoDTO;
import com.example.escuela.excepciones.AlumnoExcepcion;
import com.example.escuela.excepciones.GradoExcepcion;
import com.example.escuela.excepciones.MatriculaExcepcion;
import com.example.escuela.model.Alumno;
import com.example.escuela.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @return Una respuesta con el alumno creado o un mensaje de error si ocurre
     *         una excepción.
     */
    @PostMapping
    public ResponseEntity<?> crearAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        try {
            Alumno alumnoCreado = alumnoService.crearAlumno(alumnoDTO);
            return ResponseEntity.ok(alumnoCreado);
        } catch (MatriculaExcepcion | GradoExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
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
     * @return Una respuesta con el alumno actualizado o un mensaje de error si
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @RequestBody AlumnoDTO alumnoDTO) {
        try {
            Alumno alumnoCreado = alumnoService.actualizarAlumno(id, alumnoDTO);
            return ResponseEntity.ok(alumnoCreado);
        } catch (AlumnoExcepcion | GradoExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Elimina un alumno por su ID.
     * 
     * @param id Identificador del alumno a eliminar.
     * @return Una respuesta con el alumno eliminado o un mensaje de error si ocurre
     * una excepción.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer id) {
        try {
            Alumno alumnoEliminado = alumnoService.obtenerAlumnoPorId(id);
            alumnoService.eliminarAlumno(id);
            return ResponseEntity.ok(alumnoEliminado);
        } catch (AlumnoExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
