package com.example.escuela.controller;

import com.example.escuela.model.Grado;
import com.example.escuela.service.GradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con los grados.
 * Permite crear, listar, obtener y eliminar grados.
 * 
 * Anotaciones:
 * - @RestController: Indica que esta clase es un controlador REST.
 * - @CrossOrigin: Permite solicitudes de origen cruzado desde cualquier origen.
 * - @RequestMapping: Define la ruta base para todas las operaciones de este
 * controlador.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/grados")
public class GradoController {

    /**
     * Servicio para manejar la lógica de negocio relacionada con los grados.
     * Se inyecta automáticamente por Spring.
     */
    @Autowired
    private GradoService gradoService;

    /**
     * Constructor por defecto para la clase GradoController.
     */
    public GradoController() {
    }

    /**
     * Crea un nuevo grado.
     * 
     * @param grado Objeto que contiene los datos del grado a crear.
     * @return El grado creado.
     */
    @PostMapping
    public Grado crearGrado(@RequestBody Grado grado) {
        return gradoService.crearGrado(grado);
    }

    /**
     * Actualiza un grado existente por su ID.
     * 
     * @param id    Identificador del grado a actualizar.
     * @param grado Objeto que contiene los nuevos datos del grado.
     * @return El grado actualizado.
     */
    @PutMapping("/{id}")
    public Grado actualizarGrado(@PathVariable Integer id, @RequestBody Grado grado) {
        return gradoService.actualizarGrado(id, grado);
    }

    /**
     * Lista todos los grados.
     * 
     * @return Una lista de todos los grados disponibles.
     */
    @GetMapping
    public List<Grado> listarGrados() {
        return gradoService.listarGrados();
    }

    /**
     * Obtiene un grado por su ID.
     * 
     * @param id Identificador del grado a obtener.
     * @return El grado correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public Grado obtenerGrado(@PathVariable Integer id) {
        return gradoService.obtenerGradoPorId(id);
    }

    /**
     * Elimina un grado por su ID.
     * 
     * @param id Identificador del grado a eliminar.
     * @return El grado eliminado.
     */
    @DeleteMapping("/{id}")
    public Grado eliminarGrado(@PathVariable Integer id) {
        Grado grado = gradoService.obtenerGradoPorId(id);
        gradoService.eliminarGrado(id);
        return grado;
    }
}
