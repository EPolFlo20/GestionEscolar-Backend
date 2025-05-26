package com.example.escuela.service.impl;

import com.example.dto.AlumnoDTO;
import com.example.escuela.model.Alumno;
import com.example.escuela.model.Grado;
import com.example.escuela.repository.AlumnoRepository;
import com.example.escuela.repository.GradoRepository;
import com.example.escuela.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio AlumnoService.
 * Proporciona métodos para crear, actualizar, eliminar y obtener información de alumnos.
 * Utiliza los repositorios AlumnoRepository y GradoRepository para interactuar con la base de datos.
 */
@Service
public class AlumnoServiceImpl implements AlumnoService {

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad Alumno.
     */
    @Autowired
    private AlumnoRepository alumnoRepository;

    /**
     * Repositorio para realizar operaciones CRUD sobre la entidad Grado.
     * Se utiliza para obtener el grado al que pertenece un alumno.
     */
    @Autowired
    private GradoRepository gradoRepository;

    /**
     * Constructor por defecto para la clase AlumnoServiceImpl.
     */
    public AlumnoServiceImpl() {
    }
        

    /**
     * Crea un nuevo alumno en la base de datos.
     * 
     * @param alumnoDTO Objeto que contiene los datos del alumno a crear.
     * @return El alumno creado.
     */
    @Override
    public Alumno crearAlumno(AlumnoDTO alumnoDTO) {

        Grado grado = gradoRepository.findById(alumnoDTO.getId_grado())
                .orElseThrow(() -> new RuntimeException("Grado no encontrado"));

        Alumno alumno = new Alumno();
        alumno.setMatricula(alumnoDTO.getMatricula());
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setCorreoElectronico(alumnoDTO.getCorreoElectronico());
        alumno.setGrado(grado);

        return alumnoRepository.save(alumno);
    }

    /**
     * Actualiza un alumno existente en la base de datos.
     * 
     * @param id El identificador del alumno a actualizar.
     * @param alumnoDTO Objeto que contiene los nuevos datos del alumno.
     * @return El alumno actualizado.
     */
    @Override
    public Alumno actualizarAlumno(Integer id, AlumnoDTO alumnoDTO) {
        Optional<Alumno> existente = alumnoRepository.findById(id);
        if (existente.isPresent()) {
            Grado grado = gradoRepository.findById(alumnoDTO.getId_grado())
                    .orElseThrow(() -> new RuntimeException("Grado no encontrado"));

            Alumno alumno = existente.get();
            alumno.setMatricula(alumnoDTO.getMatricula());
            alumno.setNombre(alumnoDTO.getNombre());
            alumno.setCorreoElectronico(alumnoDTO.getCorreoElectronico());
            alumno.setGrado(grado);
            return alumnoRepository.save(alumno);
        } else {
            throw new RuntimeException("Alumno no encontrado con id " + id);
        }
    }

    /**
     * Elimina un alumno de la base de datos.
     * 
     * @param id El identificador del alumno a eliminar.
     */
    @Override
    public void eliminarAlumno(Integer id) {
        alumnoRepository.deleteById(id);
    }

    /**
     * Obtiene un alumno por su identificador.
     * 
     * @param id El identificador del alumno a obtener.
     * @return El alumno encontrado.
     */
    @Override
    public Alumno obtenerAlumnoPorId(Integer id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id " + id));
    }

    /**
     * Lista todos los alumnos registrados en la base de datos.
     * 
     * @return Una lista de todos los alumnos.
     */
    @Override
    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }
}
