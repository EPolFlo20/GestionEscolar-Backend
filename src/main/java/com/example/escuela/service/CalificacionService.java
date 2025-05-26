package com.example.escuela.service;

import com.example.dto.CalificacionDTO;
import com.example.escuela.model.Calificacion;
import java.util.List;

/**
 * Interfaz del servicio CalificacionService.
 * Proporciona métodos para asignar, actualizar y obtener calificaciones de alumnos en asignaturas.
 * Utiliza el modelo Calificacion y el DTO CalificacionDTO para las operaciones.
 */
public interface CalificacionService {
    /**
     * Asigna una calificación a un alumno en una asignatura específica.
     *
     * @param calificacionDTO Objeto que contiene los datos de la calificación a asignar.
     * @return La calificación asignada.
     */
    Calificacion asignarCalificacion(CalificacionDTO calificacionDTO);

    /**
     * Actualiza una calificación existente.
     *
     * @param id El identificador de la calificación a actualizar.
     * @param calificacionDTO Objeto que contiene los nuevos datos de la calificación.
     * @return La calificación actualizada.
     */
    Calificacion actualizarCalificacion(Integer id, CalificacionDTO calificacionDTO);

    /**
     * Obtiene todas las calificaciones registradas.
     * @return Una lista de todas las calificaciones.
     */
    List<Calificacion> obtenerCalificaciones();

    /**
     * Obtiene una calificación por el identificador del alumno.
     * @param idAlumno El identificador del alumno cuyas calificaciones se desean obtener.
     * @return Una lista de calificaciones del alumno.
     */
    List<Calificacion> obtenerCalificacionesPorAlumno(Integer idAlumno);

    /**
     * Obtiene las calificaciones de una asignatura específica.
     *
     * @param idAsignatura El identificador de la asignatura cuyas calificaciones se desean obtener.
     * @return Una lista de calificaciones asociadas a la asignatura.
     */
    List<Calificacion> obtenerCalificacionesPorAsignatura(Integer idAsignatura);   

    /**
     * Elimina una calificación por su identificador.
     *
     * @param id El identificador de la calificación a eliminar.
     */
    void eliminarCalificacion(Integer id);

    /**
     * Obtiene una calificación por su identificador.
     *
     * @param id El identificador de la calificación a obtener.
     * @return La calificación correspondiente al identificador proporcionado.
     */
    Calificacion obtenerCalificacionPorId(Integer id);
}