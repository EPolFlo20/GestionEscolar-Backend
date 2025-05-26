package com.example.escuela.service.impl;

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
     */
    @Override
    public Grado crearGrado(Grado grado) {
        return gradoRepository.save(grado);
    }

    /**
     * Obtiene un grado por su identificador.
     * @param id El identificador del grado a obtener.
     * @return El grado correspondiente al identificador, o null si no se encuentra.
     */
    @Override
    public Grado obtenerGradoPorId(Integer id) {
        return gradoRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene una lista de todos los grados.
     * @return Una lista de todos los grados en la base de datos.
     */
    @Override
    public List<Grado> listarGrados() {
        return gradoRepository.findAll();
    }

    /**
     * Elimina un grado por su identificador.
     * @param id El identificador del grado a eliminar.
     */
    @Override
    public void eliminarGrado(Integer id) {
        gradoRepository.deleteById(id);
    }
}
