package com.example.escuela.service.impl;

import com.example.dto.AsignaturaDTO;
import com.example.escuela.model.Asignatura;
import com.example.escuela.model.Grado;
import com.example.escuela.repository.AsignaturaRepository;
import com.example.escuela.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.escuela.repository.GradoRepository;

/**
 * Implementación del servicio AsignaturaService.
 * Proporciona métodos para crear, actualizar, eliminar y obtener información de asignaturas.
 * Utiliza los repositorios AsignaturaRepository y GradoRepository para interactuar con la base de datos.
 */
@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad Asignatura.
     */
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad Grado.
     * Se utiliza para obtener el grado al que pertenece una asignatura.
     */
    @Autowired
    private GradoRepository gradoRepository;

    /**
     * Crea una nueva asignatura en la base de datos.
     *
     * @param asignaturaDTO Objeto que contiene los datos de la asignatura a crear.
     * @return La asignatura creada.
     */
    @Override
    public Asignatura crearAsignatura(AsignaturaDTO asignaturaDTO) {

        Grado grado = gradoRepository.findById(asignaturaDTO.getId_grado())
                .orElseThrow(() -> new RuntimeException("Grado no encontrado"));

        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(asignaturaDTO.getNombre());
        asignatura.setGrado(grado);

        return asignaturaRepository.save(asignatura);
    }

    /**
     * Actualiza una asignatura existente en la base de datos.
     *
     * @param id El identificador de la asignatura a actualizar.
     * @param asignaturaDTO Objeto que contiene los nuevos datos de la asignatura.
     * @return La asignatura actualizada.
     */
    @Override
    public Asignatura actualizarAsignatura(Integer id, AsignaturaDTO asignaturaDTO) {
        Optional<Asignatura> existente = asignaturaRepository.findById(id);
        if (existente.isPresent()) {
            Grado grado = gradoRepository.findById(asignaturaDTO.getId_grado())
                    .orElseThrow(() -> new RuntimeException("Grado no encontrado"));

            Asignatura a = existente.get();
            a.setNombre(asignaturaDTO.getNombre());
            a.setGrado(grado);
            return asignaturaRepository.save(a);
        } else {
            throw new RuntimeException("Asignatura no encontrada con id: " + id);
        }
    }

    /**
     * Elimina una asignatura de la base de datos.
     *
     * @param id El identificador de la asignatura a eliminar.
     */
    @Override
    public void eliminarAsignatura(Integer id) {
        asignaturaRepository.deleteById(id);
    }

    /**
     * Obtiene una lista de todas las asignaturas en la base de datos.
     *
     * @return Una lista de asignaturas.
     */
    @Override
    public List<Asignatura> obtenerAsignaturas() {
        return asignaturaRepository.findAll();
    }

    /**
     * Obtiene una lista de asignaturas asociadas a un grado específico.
     *
     * @param idGrado El identificador del grado.
     * @return Una lista de asignaturas que pertenecen al grado especificado.
     */
    @Override
    public List<Asignatura> obtenerAsignaturasPorGrado(Integer idGrado) {
        return asignaturaRepository.findByGradoId(idGrado);
    }

    /**
     * Obtiene una asignatura por su identificador.
     *
     * @param id El identificador de la asignatura a obtener.
     * @return La asignatura encontrada.
     * @throws RuntimeException Si no se encuentra una asignatura con el id especificado.
     */
    @Override
    public Asignatura obtenerAsignaturaPorId(Integer id) {
        Optional<Asignatura> existente = asignaturaRepository.findById(id);
        if (existente.isPresent()) {
            return existente.get();
        } else {
            throw new RuntimeException("Asignatura no encontrada con id: " + id);
        }
    }
}