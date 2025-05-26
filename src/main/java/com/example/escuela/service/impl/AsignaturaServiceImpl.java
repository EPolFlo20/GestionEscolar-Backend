package com.example.escuela.service.impl;

import com.example.dto.AsignaturaDTO;
import com.example.escuela.excepciones.AsignaturaExcepcion;
import com.example.escuela.excepciones.GradoExcepcion;
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
 * Proporciona métodos para crear, actualizar, eliminar y obtener información de
 * asignaturas.
 * Utiliza los repositorios AsignaturaRepository y GradoRepository para
 * interactuar con la base de datos.
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
     * Constructor por defecto para la clase AsignaturaServiceImpl.
     */
    public AsignaturaServiceImpl() {
    }

    /**
     * Crea una nueva asignatura en la base de datos.
     *
     * @param asignaturaDTO Objeto que contiene los datos de la asignatura a crear.
     * @return La asignatura creada.
     * @throws GradoExcepcion      Si el grado no se encuentra en la base de datos.
     * @throws AsignaturaExcepcion Si la asignatura ya está registrada.
     */
    @Override
    public Asignatura crearAsignatura(AsignaturaDTO asignaturaDTO) {

        Grado grado = recuperarGrado(asignaturaDTO);
        validarNombre(asignaturaDTO.getNombre());

        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(asignaturaDTO.getNombre());
        asignatura.setGrado(grado);

        return asignaturaRepository.save(asignatura);
    }

    /**
     * Valida que la materia proporcionada no esté ya registrada en la base de
     * datos.
     * 
     * @param nombreAsignaturaDTO El nombre de la materia a validar.
     * @throws AsignaturaExcepcion Si la materia ya está registrada.
     */
    public void validarNombre(String nombreAsignaturaDTO) {
        List<Asignatura> asignaturas = obtenerAsignaturas();
        for (Asignatura asignatura : asignaturas) {
            String nombreAsignatura = asignatura.getNombre();
            if (nombreAsignatura.equals(nombreAsignaturaDTO)) {
                throw new AsignaturaExcepcion("Esta materia ya está registrada");
            }
        }
    }

    /**
     * Recupera el grado correspondiente al identificador proporcionado en el DTO
     * del alumno.
     * 
     * @param asignaturaDTO Objeto que contiene el identificador del grado.
     * @return El grado correspondiente al identificador.
     * @throws GradoExcepcion Si el grado no se encuentra en la base de datos.
     */
    public Grado recuperarGrado(AsignaturaDTO asignaturaDTO) {
        Grado grado = gradoRepository.findById(asignaturaDTO.getId_grado())
                .orElseThrow(() -> new GradoExcepcion("Grado no encontrado"));
        return grado;
    }

    /**
     * Actualiza una asignatura existente en la base de datos.
     *
     * @param id            El identificador de la asignatura a actualizar.
     * @param asignaturaDTO Objeto que contiene los nuevos datos de la asignatura.
     * @return La asignatura actualizada.
     * @throws AsignaturaExcepcion Si no se encuentra una asignatura con el id
     * @throws GradoExcepcion      Si el grado no se encuentra en la base de datos.
     */
    @Override
    public Asignatura actualizarAsignatura(Integer id, AsignaturaDTO asignaturaDTO) {
        Optional<Asignatura> existente = asignaturaRepository.findById(id);
        if (existente.isPresent()) {
            Grado grado = recuperarGrado(asignaturaDTO);
            validarNombre(asignaturaDTO.getNombre());

            Asignatura a = existente.get();
            a.setNombre(asignaturaDTO.getNombre());
            a.setGrado(grado);
            return asignaturaRepository.save(a);
        } else {
            throw new AsignaturaExcepcion("Asignatura no encontrada con id: " + id);
        }
    }

    /**
     * Elimina una asignatura de la base de datos.
     *
     * @param id El identificador de la asignatura a eliminar.
     * @throws AsignaturaExcepcion Si no se encuentra una asignatura con el id
     *                             especificado.
     */
    @Override
    public void eliminarAsignatura(Integer id) {
        try {
            asignaturaRepository.deleteById(id);
        } catch (Exception e) {
            throw new AsignaturaExcepcion("Error al eliminar la asignatura. Asegurese que no esté asociada a un grado o alumno.");
        }
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
     * @throws GradoExcepcion Si no se encuentra un grado con el id especificado.
     */
    @Override
    public List<Asignatura> obtenerAsignaturasPorGrado(Integer idGrado) {
        Grado grado = gradoRepository.findById(idGrado)
                .orElseThrow(() -> new GradoExcepcion("Grado no encontrado con id: " + idGrado));
        return asignaturaRepository.findByGradoId(grado.getId());
    }

    /**
     * Obtiene una asignatura por su identificador.
     *
     * @param id El identificador de la asignatura a obtener.
     * @return La asignatura encontrada.
     * @throws AsignaturaExcepcion Si no se encuentra una asignatura con el id
     *                          especificado.
     */
    @Override
    public Asignatura obtenerAsignaturaPorId(Integer id) {
        Optional<Asignatura> existente = asignaturaRepository.findById(id);
        if (existente.isEmpty()) {
            throw new AsignaturaExcepcion("Asginatura no encontrada con id: " + id);
        }
        return existente.get();
    }
}