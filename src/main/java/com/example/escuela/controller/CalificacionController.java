package com.example.escuela.controller;

import com.example.dto.CalificacionDTO;
import com.example.escuela.excepciones.AlumnoExcepcion;
import com.example.escuela.excepciones.AsignaturaExcepcion;
import com.example.escuela.excepciones.CalificacionExcepcion;
import com.example.escuela.model.Calificacion;
import com.example.escuela.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para manejar las operaciones relacionadas con las
 * calificaciones.
 * Permite asignar, actualizar y obtener calificaciones de alumnos en
 * asignaturas.
 * 
 * Anotaciones:
 * - @RestController: Indica que esta clase es un controlador REST.
 * - @CrossOrigin: Permite solicitudes de origen cruzado desde cualquier origen.
 * - @RequestMapping: Define la ruta base para todas las operaciones de este
 * controlador.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    /**
     * Servicio para manejar la lógica de negocio relacionada con las
     * calificaciones.
     */
    @Autowired
    private CalificacionService calificacionService;

    /**
     * Constructor por defecto para la clase CalificacionController.
     */
    public CalificacionController() {
    }

    /**
     * Asigna una calificación a un alumno en una asignatura.
     * 
     * @param calificacionDTO Objeto que contiene los datos de la calificación a
     *                        asignar.
     * @return La calificación asignada o un mensaje de error si ocurre una
     *         excepción.
     */
    @PostMapping
    public ResponseEntity<?> asignarCalificacion(@RequestBody CalificacionDTO calificacionDTO) {
        try {
            Calificacion calificacion = calificacionService.asignarCalificacion(calificacionDTO);
            return ResponseEntity.ok(calificacion);
        } catch (AlumnoExcepcion | AsignaturaExcepcion | CalificacionExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Actualiza una calificación existente por su ID.
     * 
     * @param id              Identificador de la calificación a actualizar.
     * @param calificacionDTO Objeto que contiene los nuevos datos de la
     *                        calificación.
     * @return La calificación actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCalificacion(@PathVariable Integer id,
            @RequestBody CalificacionDTO calificacionDTO) {
        try {
            Calificacion calificacionActualizada = calificacionService.actualizarCalificacion(id, calificacionDTO);
            return ResponseEntity.ok(calificacionActualizada);
        } catch (CalificacionExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Obtiene todas las calificaciones.
     * 
     * @return Lista de todas las calificaciones.
     */
    @GetMapping
    public List<Calificacion> obtenerCalificaciones() {
        return calificacionService.obtenerCalificaciones();
    }

    /**
     * Obtiene una calificación por el ID del alumno.
     * 
     * @param idAlumno Identificador del alumno.
     * @return La calificación correspondiente al ID proporcionado.
     */
    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<?> obtenerPorAlumno(@PathVariable Integer idAlumno) {
        try {
            List<Calificacion> calificaciones = calificacionService.obtenerCalificacionesPorAlumno(idAlumno);
            return ResponseEntity.ok(calificaciones);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    /**
     * Obtiene las calificaciones de una asignatura específica.
     * 
     * @param idAsignatura Identificador de la asignatura cuyas calificaciones se
     *                     desean obtener.
     * @return Lista de calificaciones asociadas a la asignatura.
     */
    @GetMapping("/asignatura/{idAsignatura}")
    public ResponseEntity<?> obtenerPorAsignatura(@PathVariable Integer idAsignatura) {
        try {
            List<Calificacion> calificaciones = calificacionService.obtenerCalificacionesPorAsignatura(idAsignatura);
            return ResponseEntity.ok(calificaciones);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
