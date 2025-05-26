package com.example.escuela.service;

import com.example.dto.AsignaturaDTO;
import com.example.escuela.model.Asignatura;

import java.util.List;

/**
 * Interfaz del servicio AsignaturaService.
 * Proporciona métodos para crear, actualizar, eliminar y obtener información de asignaturas.
 * Utiliza el modelo Asignatura y el DTO AsignaturaDTO para las operaciones.
 */
public interface AsignaturaService {
    /**
     * Crea una nueva asignatura a partir de un AsignaturaDTO.
     *
     * @param asignaturaDTO el DTO que contiene los datos de la asignatura a crear
     * @return el objeto Asignatura creado
     */
    Asignatura crearAsignatura(AsignaturaDTO asignaturaDTO);

    /**
     * Actualiza una asignatura existente.
     *
     * @param id el identificador de la asignatura a actualizar
     * @param asignaturaDTO el DTO que contiene los nuevos datos de la asignatura
     * @return el objeto Asignatura actualizado
     */
    Asignatura actualizarAsignatura(Integer id, AsignaturaDTO asignaturaDTO);

    /**
     * Elimina una asignatura por su identificador.
     *
     * @param id el identificador de la asignatura a eliminar
     */
    void eliminarAsignatura(Integer id);

    /**
     * Obtiene una lista de todas las asignaturas.
     * @return una lista de objetos Asignatura
     */
    List<Asignatura> obtenerAsignaturas();

    /**
     * Obtiene una lista de asignaturas por el identificador del grado.
     *
     * @param idGrado el identificador del grado al que pertenecen las asignaturas
     * @return una lista de objetos Asignatura correspondientes al grado
     */
    List<Asignatura> obtenerAsignaturasPorGrado(Integer idGrado);

    /**
     * Obtiene una asignatura por su identificador.
     *
     * @param id el identificador de la asignatura a obtener
     * @return el objeto Asignatura correspondiente al identificador
     */
    Asignatura obtenerAsignaturaPorId(Integer id);
}
