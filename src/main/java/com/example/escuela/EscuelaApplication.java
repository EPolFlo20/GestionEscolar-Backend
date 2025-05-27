package com.example.escuela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Escuela.
 * Esta clase inicia la aplicación Spring Boot.
 * Utiliza la anotación @SpringBootApplication para habilitar la configuración automática y el escaneo de componentes.
 */
@SpringBootApplication
public class EscuelaApplication {

	/**
	 * Método principal que inicia la aplicación.
	 * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
	 */
	public static void main(String[] args) {
		SpringApplication.run(EscuelaApplication.class, args);
	}

	/**
	 * Constructor por defecto para la clase EscuelaApplication.
	 */
	public EscuelaApplication() {
	}

}
