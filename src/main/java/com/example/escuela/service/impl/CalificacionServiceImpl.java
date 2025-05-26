package com.example.escuela.service.impl;

import com.example.dto.CalificacionDTO;
import com.example.escuela.model.Alumno;
import com.example.escuela.model.Asignatura;
import com.example.escuela.model.Calificacion;
import com.example.escuela.repository.AlumnoRepository;
import com.example.escuela.repository.AsignaturaRepository;
import com.example.escuela.repository.CalificacionRepository;
import com.example.escuela.service.CalificacionService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio CalificacionService.
 * Proporciona métodos para asignar, actualizar y obtener calificaciones de alumnos en asignaturas.
 * Utiliza los repositorios CalificacionRepository, AlumnoRepository y AsignaturaRepository para interactuar con la base de datos.
 */
@Service
public class CalificacionServiceImpl implements CalificacionService {

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad Calificacion.
     */
    @Autowired
    private CalificacionRepository calificacionRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad Alumno.
     * Se utiliza para obtener el alumno al que se le asigna la calificación.
     */
    @Autowired
    private AlumnoRepository alumnoRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad Asignatura.
     * Se utiliza para obtener la asignatura a la que se le asigna la calificación.
     */
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    /**
     * Asigna una calificación a un alumno en una asignatura específica.
     *
     * @param calificacionDTO Objeto que contiene los datos de la calificación a asignar.
     * @return La calificación asignada.
     */
    @Override
    public Calificacion asignarCalificacion(CalificacionDTO calificacionDTO) {

        if (calificacionDTO.getCalificacion() < 0 || calificacionDTO.getCalificacion() > 100) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 100.");
        }

        Alumno alumno = alumnoRepository.findById(calificacionDTO.getId_alumno())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(calificacionDTO.getId_asignatura())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        Calificacion calificacion = new Calificacion();
        calificacion.setAlumno(alumno);
        calificacion.setAsignatura(asignatura);
        calificacion.setCalificacion(calificacionDTO.getCalificacion());

        return calificacionRepository.save(calificacion);
    }

    /**
     * Actualiza una calificación existente.
     *
     * @param id El identificador de la calificación a actualizar.
     * @param calificacionDTO Objeto que contiene los nuevos datos de la calificación.
     * @return La calificación actualizada.
     */
    @Override
    public Calificacion actualizarCalificacion(Integer id, CalificacionDTO calificacionDTO) {
        if (calificacionDTO.getCalificacion() < 0 || calificacionDTO.getCalificacion() > 100) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 100.");
        }

        Optional<Calificacion> existente = calificacionRepository.findById(id);

        if (existente.isPresent()) {
            Calificacion calificacion = existente.get();

            calificacion.setCalificacion(calificacionDTO.getCalificacion());

            return calificacionRepository.save(calificacion);
        } else {
            throw new RuntimeException("Calificación no encontrada con id: " + id);
        }
    }

    /**
     * Obtiene una lista de todas las calificaciones en la base de datos.
     * @return Una lista de calificaciones.
     */
    @Override
    public List<Calificacion> obtenerCalificaciones() {
        return calificacionRepository.findAll();
    }

    /**
     * Obtiene una calificación por el identificador del alumno que la posee.
     * @param idAlumno El identificador del alumno cuyas calificaciones se desean obtener.
     * @return Una lista de calificaciones del alumno especificado.
     */
    @Override
    public List<Calificacion> obtenerCalificacionesPorAlumno(Integer idAlumno) {
        return calificacionRepository.findByAlumnoId(idAlumno);
    }

    /**
     * Obtiene una lista de calificaciones por asignatura.
     * @param idAsignatura El identificador de la asignatura cuyas calificaciones se desean obtener.
     */
    @Override
    public List<Calificacion> obtenerCalificacionesPorAsignatura(Integer idAsignatura) {
        return calificacionRepository.findByAsignaturaId(idAsignatura);
    }
}