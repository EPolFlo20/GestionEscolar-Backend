package com.example.escuela.service.impl;

import com.example.dto.AlumnoDTO;
import com.example.escuela.excepciones.AlumnoExcepcion;
import com.example.escuela.excepciones.GradoExcepcion;
import com.example.escuela.excepciones.MatriculaExcepcion;
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
 * Proporciona métodos para crear, actualizar, eliminar y obtener información de
 * alumnos.
 * Utiliza los repositorios AlumnoRepository y GradoRepository para interactuar
 * con la base de datos.
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
     * @throws MatriculaExcepcion Si la matrícula ya está registrada para otro
     *                            alumno.
     * @throws GradoExcepcion     Si el grado no se encuentra en la base de datos.
     */
    @Override
    public Alumno crearAlumno(AlumnoDTO alumnoDTO) {

        String matriculaDTO = alumnoDTO.getMatricula();

        Grado grado = recuperarGrado(alumnoDTO);
        validarMatricula(matriculaDTO);

        Alumno alumno = new Alumno();
        alumno.setMatricula(alumnoDTO.getMatricula());
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setCorreoElectronico(alumnoDTO.getCorreoElectronico());
        alumno.setGrado(grado);

        return alumnoRepository.save(alumno);
    }

    /**
     * Valida que la matrícula proporcionada no esté ya registrada para otro
     * alumno.
     * 
     * @param matriculaDTO La matrícula a validar.
     * @throws MatriculaExcepcion Si la matrícula ya está registrada.
     */
    public void validarMatricula(String matriculaDTO) {
        List<Alumno> alumnos = listarAlumnos();
        for (Alumno alumno : alumnos) {
            String matriculaAlumno = alumno.getMatricula();
            if (matriculaAlumno.equals(matriculaDTO)) {
                throw new MatriculaExcepcion("La mátricula ya está registrada para otro alumno.");
            }
        }
    }

    /**
     * Recupera el grado correspondiente al identificador proporcionado en el DTO
     * del alumno.
     * 
     * @param alumnoDTO Objeto que contiene el identificador del grado.
     * @return El grado correspondiente al identificador.
     * @throws GradoExcepcion Si el grado no se encuentra en la base de datos.
     */
    public Grado recuperarGrado(AlumnoDTO alumnoDTO) {
        Grado grado = gradoRepository.findById(alumnoDTO.getId_grado())
                .orElseThrow(() -> new GradoExcepcion("Grado no encontrado"));
        return grado;
    }

    /**
     * Actualiza un alumno existente en la base de datos.
     * 
     * @param id        El identificador del alumno a actualizar.
     * @param alumnoDTO Objeto que contiene los nuevos datos del alumno.
     * @return El alumno actualizado.
     * @throws AlumnoExcepcion Si el alumno no se encuentra en la base de datos.
     * @throws GradoExcepcion  Si el grado no se encuentra en la base de datos.
     */
    @Override
    public Alumno actualizarAlumno(Integer id, AlumnoDTO alumnoDTO) {
        Optional<Alumno> existente = alumnoRepository.findById(id);
        if (!existente.isPresent()) {
            throw new AlumnoExcepcion("Alumno no encontrado con id " + id);
        }
        Grado grado = recuperarGrado(alumnoDTO);

        Alumno alumno = existente.get();
        alumno.setMatricula(alumnoDTO.getMatricula());
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setCorreoElectronico(alumnoDTO.getCorreoElectronico());
        alumno.setGrado(grado);
        return alumnoRepository.save(alumno);
    }

    /**
     * Elimina un alumno de la base de datos.
     * 
     * @param id El identificador del alumno a eliminar.
     */
    @Override
    public void eliminarAlumno(Integer id) {
        try {
            alumnoRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new AlumnoExcepcion("No se puede eliminar, alumno no encontrado con id " + id);
        }
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
                .orElseThrow(() -> new AlumnoExcepcion("No se econtró al alumno con id " + id));
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
