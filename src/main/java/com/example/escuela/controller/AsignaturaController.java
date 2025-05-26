package com.example.escuela.controller;

import com.example.dto.AsignaturaDTO;
import com.example.escuela.model.Asignatura;
import com.example.escuela.service.AsignaturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con las asignaturas.
 * Permite crear, listar, obtener, actualizar y eliminar asignaturas.
 * 
 * Anotaciones:
 * @RestController: Indica que esta clase es un controlador REST.
 * @CrossOrigin: Permite solicitudes de origen cruzado desde cualquier origen.
 * @RequestMapping: Define la ruta base para todas las operaciones de este controlador.
 */
@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    /**
     * Servicio para manejar la lógica de negocio relacionada con las asignaturas.
     */
    @Autowired
    private AsignaturaService asignaturaService;

    /**
     * Constructor del controlador AsignaturaController.
     * 
     * @param asignaturaDTO El DTO que contiene los datos de la asignatura a crear.
     * @return La asignatura creada.
     */
    @PostMapping
    public Asignatura crearAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        return asignaturaService.crearAsignatura(asignaturaDTO);
    }

    /**
     * Actualiza una asignatura existente.
     * 
     * @param id Identificador de la asignatura a actualizar.
     * @param asignaturaDTO Objeto que contiene los nuevos datos de la asignatura.
     * @return La asignatura actualizada.
     */
    @PutMapping("/{id}")
    public Asignatura actualizarAsignatura(@PathVariable Integer id, @RequestBody AsignaturaDTO asignaturaDTO) {
        return asignaturaService.actualizarAsignatura(id, asignaturaDTO);
    }

    /**
     * Obtiene una asignatura por su ID.
     * 
     * @param id Identificador de la asignatura a obtener.
     * @return La asignatura correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public Asignatura obtenerAsignaturaPorId(@PathVariable Integer id) {
        return asignaturaService.obtenerAsignaturaPorId(id);
    }

    /**
     * Lista todas las asignaturas.
     * 
     * @return Lista de asignaturas.
     */
    @GetMapping
    public List<Asignatura> obtenerAsignaturas() {
        return asignaturaService.obtenerAsignaturas();
    }

    /**
     * Obtiene las asignaturas asociadas a un grado específico.
     * 
     * @param idGrado Identificador del grado para el cual se desean obtener las asignaturas.
     * @return Lista de asignaturas asociadas al grado especificado.
     */
    @GetMapping("/grado/{idGrado}")
    public List<Asignatura> obtenerAsignaturasPorGrado(@PathVariable Integer idGrado) {
        return asignaturaService.obtenerAsignaturasPorGrado(idGrado);
    }

    /**
     * Elimina una asignatura por su ID.
     * 
     * @param id Identificador de la asignatura a eliminar.
     * @return La asignatura eliminada.
     */
    @DeleteMapping("/{id}")
    public Asignatura eliminarAsignatura(@PathVariable Integer id) {
        Asignatura asignatura = asignaturaService.obtenerAsignaturaPorId(id);
        asignaturaService.eliminarAsignatura(id);
        return asignatura;
    }
}