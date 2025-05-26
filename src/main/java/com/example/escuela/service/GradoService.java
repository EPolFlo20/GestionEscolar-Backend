package com.example.escuela.service;

import com.example.escuela.model.Grado;
import java.util.List;

/**
 * Interfaz del servicio GradoService.
 * Proporciona métodos para crear, obtener, listar y eliminar grados.
 * Utiliza el modelo Grado para las operaciones.
 */
public interface GradoService {
    /**
     * Crea un nuevo grado en la base de datos.
     *
     * @param grado Objeto que contiene los datos del grado a crear.
     * @return El grado creado.
     */
    Grado crearGrado(Grado grado);

    /**
     * Actualiza un grado existente por su identificador.
     *
     * @param id El identificador del grado a actualizar.
     * @param grado Objeto que contiene los nuevos datos del grado.
     * @return El grado actualizado.
     */
    Grado actualizarGrado(Integer id, Grado grado);

    /**
     * Obtiene un grado por su identificador.
     *
     * @param id El identificador del grado a obtener.
     * @return El grado correspondiente al identificador, o null si no se encuentra.
     */
    Grado obtenerGradoPorId(Integer id);

    /**
     * Obtiene una lista de todos los grados.
     *
     * @return Una lista de todos los grados en la base de datos.
     */
    List<Grado> listarGrados();

    /**
     * Elimina un grado por su identificador.
     *
     * @param id El identificador del grado a eliminar.
     */
    void eliminarGrado(Integer id);
}
