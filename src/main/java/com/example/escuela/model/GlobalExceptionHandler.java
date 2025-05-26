package com.example.escuela.model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.escuela.excepciones.AlumnoExcepcion;
import com.example.escuela.excepciones.GradoExcepcion;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase para manejar excepciones globales en la aplicación.
 * Utiliza @ControllerAdvice para interceptar excepciones lanzadas por los
 * controladores y devolver respuestas adecuadas.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo MethodArgumentTypeMismatchException.
     * Captura errores de tipo de parámetro y devuelve un mapa con el error.
     *
     * @param ex La excepción lanzada.
     * @return Una respuesta con un mapa de error y un código de estado 400 Bad
     *         Request.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Tipo de parámetro inválido");
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Maneja excepciones de validación de argumentos de método.
     * Captura errores de validación en los DTOs y devuelve un mapa con los errores.
     *
     * @param ex La excepción lanzada.
     * @return Una respuesta con un mapa de errores y un código de estado 400 Bad
     *         Request.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errores.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones específicas de la entidad Alumno.
     * Captura errores relacionados con operaciones de alumnos y devuelve un mapa con
     * el error.
     *
     * @param ex La excepción lanzada.
     * @return Una respuesta con un mapa de error y un código de estado 404 Not
     *         Found.
     */
    @ExceptionHandler(AlumnoExcepcion.class)
    public ResponseEntity<Map<String, String>> handleAlumnoExcepcion(AlumnoExcepcion ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error con el alumno");
        error.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GradoExcepcion.class)
    public ResponseEntity<Map<String, String>> handleGradoExcepcion(GradoExcepcion ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error con el grado");
        error.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Catch-all para cualquier otra excepción
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error interno");
        error.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
