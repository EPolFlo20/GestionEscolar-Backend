package com.example.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para representar una Asignatura.
 * Guarda el nombre de la asignatura y el id del grado en que se imparte.
 * 
 * Fields:
 * <ul>
 *   <li>nombre - Nombre de la asignatura.</li>
 *   <li>id_grado - Id del grado en que se imparte.</li>
 * </ul>
 */
@Data
public class AsignaturaDTO {
    private String nombre;
    private Integer id_grado;
    
    /**
     * Constructor por defecto para la clase AsignaturaDTO.
     */
    public AsignaturaDTO() {
    }
}
