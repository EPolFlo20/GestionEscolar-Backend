package com.example.escuela.excepciones;

/**
 * Excepción personalizada para manejar errores relacionados con calificaciones.
 * Extiende RuntimeException para permitir que se lance sin necesidad de
 * declarar la excepción en los métodos.
 */
public class CalificacionExcepcion extends RuntimeException{

    /**
     * Constructor por defecto para la clase CalificacionExcepcion.
     * @param message Mensaje de error que describe la excepción.
     */
    public CalificacionExcepcion(String message) {
        super(message);
    }
    
}
