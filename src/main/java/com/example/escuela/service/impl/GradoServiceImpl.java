package com.example.escuela.service.impl;

import com.example.escuela.excepciones.GradoExcepcion;
import com.example.escuela.model.Grado;
import com.example.escuela.repository.GradoRepository;
import com.example.escuela.service.GradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio GradoService.
 * Proporciona métodos para crear, obtener, listar y eliminar grados.
 * Utiliza el repositorio GradoRepository para interactuar con la base de datos.
 */
@Service
public class GradoServiceImpl implements GradoService {

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad Grado.
     */
    @Autowired
    private GradoRepository gradoRepository;

    /**
     * Constructor por defecto para la clase GradoServiceImpl.
     */
    public GradoServiceImpl() {
    }

    /**
     * Crea un nuevo grado en la base de datos.
     *
     * @param grado Objeto que contiene los datos del grado a crear.
     * @return El grado creado.
     * @throws GradoExcepcion Si ya existe un grado con el mismo nombre.
     */
    @Override
    public Grado crearGrado(Grado grado) {
        verificarGradoNoExistente(grado);
        return gradoRepository.save(grado);
    }

    /**
     * Actualiza un grado existente por su identificador.
     *
     * @param id    El identificador del grado a actualizar.
     * @param grado Objeto que contiene los nuevos datos del grado.
     * @return El grado actualizado.
     * @throws GradoExcepcion Si el grado no existe o si ya existe un grado con el
     *                        mismo nombre.
     */
    @Override
    public Grado actualizarGrado(Integer id, Grado grado) {
        if (!gradoRepository.existsById(id)) {
            throw new GradoExcepcion("Grado con ID " + id + " no encontrado.");
        }
        verificarGradoNoExistente(grado);
        grado.setId(id);

        return gradoRepository.save(grado);
    }

    /**
     * Verifica si ya existe un grado con el mismo nombre.
     *
     * @param grado El grado a verificar.
     * @throws GradoExcepcion Si ya existe un grado con el mismo nombre.
     */
    public void verificarGradoNoExistente(Grado grado) {
        List<Grado> grados = gradoRepository.findAll();
        for (Grado g : grados) {
            if (g.getNombre_grado().equalsIgnoreCase(grado.getNombre_grado())) {
                throw new GradoExcepcion("Ya existe un grado con el nombre: " + grado.getNombre_grado());
            }
        }
    }

    /**
     * Obtiene un grado por su identificador.
     * 
     * @param id El identificador del grado a obtener.
     * @return El grado correspondiente al identificador, o null si no se encuentra.
     */
    @Override
    public Grado obtenerGradoPorId(Integer id) {
        return gradoRepository.findById(id)
                .orElseThrow(() -> new GradoExcepcion("Grado con ID " + id + " no encontrado."));
    }

    /**
     * Obtiene una lista de todos los grados.
     * 
     * @return Una lista de todos los grados en la base de datos.
     */
    @Override
    public List<Grado> listarGrados() {
        return gradoRepository.findAll();
    }

    /**
     * Elimina un grado por su identificador.
     * 
     * @param id El identificador del grado a eliminar.
     * @throws GradoExcepcion Si el grado no se encuentra o no se puede eliminar.
     */
    @Override
    public void eliminarGrado(Integer id) {
        try {
            gradoRepository.deleteById(id);
        } catch (Exception e) {
            throw new GradoExcepcion("Error al eliminar la asignatura. Asegurese que no esté asociada a una materia.");
        }
    }
}
