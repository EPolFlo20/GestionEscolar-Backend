package com.example.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para representar un Alumno.
 * Guarda la matricula, nombre, email, y el ID del grado del estudiante.
 * 
 * Fields:
 * <ul>
 *   <li>matricula - Identificador del alumno.</li>
 *   <li>nombre - Nombre completo del alumno.</li>
 *   <li>correoElectronico - Email del alumno.</li>
 *   <li>id_grado - Identificador del grado del alumno.</li>
 * </ul>
 */
@Data
public class AlumnoDTO {

    private String matricula;

    private String nombre;

    private String correoElectronico;

    private Integer id_grado;

    /**
     * Constructor por defecto para la clase AlumnoDTO.
     */
    public AlumnoDTO() {
    }

    

}
