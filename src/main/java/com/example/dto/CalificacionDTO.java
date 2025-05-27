package com.example.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para representar una Calificación.
 * Guarda el ID del alumno, el ID de la asignatura y la calificación obtenida.
 * 
 * Fields:
 * <ul>
 *  <li>id_alumno - Identificador del alumno.</li>
 *  <li>id_asignatura - Identificador de la asignatura.</li>
 *  <li>calificacion - Calificación obtenida por el alumno en la asignatura.</li>
 * </ul>
 */
@Data
public class CalificacionDTO {
    private Integer id_alumno;
    private Integer id_asignatura;
    private Float calificacion;
    
    /**
     * Constructor por defecto para la clase CalificacionDTO.
     */
    public CalificacionDTO() {
    }
}
