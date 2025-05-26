package com.example.escuela.excepciones;

/**
 * Excepción personalizada para manejar errores relacionados con alumnos.
 * Extiende RuntimeException para permitir que se lance sin necesidad de
 * declarar la excepción en los métodos.
 */
public class GradoExcepcion extends RuntimeException{

    /**
     * Constructor por defecto para la clase GradoExcepcion.
     * @param message Mensaje de error que describe la excepción.
     */
    public GradoExcepcion(String message) {
        super(message);
    }
    
}
