package com.example.escuela.controller;

import com.example.dto.CalificacionDTO;
import com.example.escuela.model.Calificacion;
import com.example.escuela.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con las calificaciones.
 * Permite asignar, actualizar y obtener calificaciones de alumnos en asignaturas.
 * 
 * Anotaciones:
 * @RestController: Indica que esta clase es un controlador REST.
 * @CrossOrigin: Permite solicitudes de origen cruzado desde cualquier origen.
 * @RequestMapping: Define la ruta base para todas las operaciones de este controlador.
 */
@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    /**
     * Servicio para manejar la lógica de negocio relacionada con las calificaciones.
     */
    @Autowired
    private CalificacionService calificacionService;

    /**
     * Asigna una calificación a un alumno en una asignatura.
     * 
     * @param calificacionDTO Objeto que contiene los datos de la calificación a asignar.
     * @return La calificación asignada.
     */
    @PostMapping
    public Calificacion asignarCalificacion(@RequestBody CalificacionDTO calificacionDTO) {
        return calificacionService.asignarCalificacion(calificacionDTO);
    }

    /**
     * Actualiza una calificación existente por su ID.
     * 
     * @param id Identificador de la calificación a actualizar.
     * @param calificacionDTO Objeto que contiene los nuevos datos de la calificación.
     * @return La calificación actualizada.
     */
    @PutMapping("/{id}")
    public Calificacion actualizarCalificacion(@PathVariable Integer id, @RequestBody CalificacionDTO calificacionDTO) {
        return calificacionService.actualizarCalificacion(id, calificacionDTO);
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
     * @param id Identificador del alumno.
     * @return La calificación correspondiente al ID proporcionado.
     */
    @GetMapping("/alumno/{idAlumno}")
    public List<Calificacion> obtenerPorAlumno(@PathVariable Integer idAlumno) {
        return calificacionService.obtenerCalificacionesPorAlumno(idAlumno);
    }

    /**
     * Obtiene las calificaciones de una asignatura específica.
     * 
     * @param idAsignatura Identificador de la asignatura cuyas calificaciones se desean obtener.
     * @return Lista de calificaciones asociadas a la asignatura.
     */
    @GetMapping("/asignatura/{idAsignatura}")
    public List<Calificacion> obtenerPorAsignatura(@PathVariable Integer idAsignatura) {
        return calificacionService.obtenerCalificacionesPorAsignatura(idAsignatura);
    }
}
