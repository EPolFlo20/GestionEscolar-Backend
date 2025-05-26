package com.example.escuela.controller;

import com.example.dto.AsignaturaDTO;
import com.example.escuela.excepciones.AsignaturaExcepcion;
import com.example.escuela.excepciones.GradoExcepcion;
import com.example.escuela.model.Asignatura;
import com.example.escuela.service.AsignaturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para manejar las operaciones relacionadas con las
 * asignaturas.
 * Permite crear, listar, obtener, actualizar y eliminar asignaturas.
 * 
 * Anotaciones:
 * - @RestController: Indica que esta clase es un controlador REST.
 * - @CrossOrigin: Permite solicitudes de origen cruzado desde cualquier origen.
 * - @RequestMapping: Define la ruta base para todas las operaciones de este
 * controlador.
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
     * Constructor por defecto para la clase AsignaturaController.
     */
    public AsignaturaController() {
    }

    /**
     * Crea una nueva asignatura con los datos proporcionados en el DTO.
     * 
     * @param asignaturaDTO El DTO que contiene los datos de la asignatura a crear.
     * @return La asignatura creada.
     */
    @PostMapping
    public ResponseEntity<?> crearAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        try {
            Asignatura asignaturaCreada = asignaturaService.crearAsignatura(asignaturaDTO);
            return ResponseEntity.ok(asignaturaCreada);
        } catch (GradoExcepcion | AsignaturaExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Actualiza una asignatura existente.
     * 
     * @param id            Identificador de la asignatura a actualizar.
     * @param asignaturaDTO Objeto que contiene los nuevos datos de la asignatura.
     * @return La asignatura actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAsignatura(@PathVariable Integer id, @RequestBody AsignaturaDTO asignaturaDTO) {
        try {
            Asignatura asignaturaActualizada = asignaturaService.actualizarAsignatura(id, asignaturaDTO);
            return ResponseEntity.ok(asignaturaActualizada);
        } catch (GradoExcepcion | AsignaturaExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Obtiene una asignatura por su ID.
     * 
     * @param id Identificador de la asignatura a obtener.
     * @return La asignatura correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAsignaturaPorId(@PathVariable Integer id) {
        try {
            Asignatura asignatura = asignaturaService.obtenerAsignaturaPorId(id);
            return ResponseEntity.ok(asignatura);
        } catch (AsignaturaExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
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
     * @param idGrado Identificador del grado para el cual se desean obtener las
     *                asignaturas.
     * @return Lista de asignaturas asociadas al grado especificado.
     */
    @GetMapping("/grado/{idGrado}")
    public ResponseEntity<?>  obtenerAsignaturasPorGrado(@PathVariable Integer idGrado) {
        try {
            List<Asignatura> asignaturas = asignaturaService.obtenerAsignaturasPorGrado(idGrado);
            return ResponseEntity.ok(asignaturas);    
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Error al obtener las asignaturas por grado: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /**
     * Elimina una asignatura por su ID.
     * 
     * @param id Identificador de la asignatura a eliminar.
     * @return La asignatura eliminada.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAsignatura(@PathVariable Integer id) {
        try {
            Asignatura asignatura = asignaturaService.obtenerAsignaturaPorId(id);
            asignaturaService.eliminarAsignatura(id);
            return ResponseEntity.ok(asignatura);
        } catch (AsignaturaExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}