package com.example.escuela.excepciones;

/**
 * Excepción personalizada para manejar errores relacionados con alumnos.
 * Extiende RuntimeException para permitir que se lance sin necesidad de
 * declarar la excepción en los métodos.
 */
public class AlumnoExcepcion extends RuntimeException {

    /**
     * Constructor por defecto para la clase AlumnoExcepcion.
     * @param message Mensaje de error que describe la excepción.
     */
    public AlumnoExcepcion(String message) {
        super(message);
    }

}
