
CREATE DATABASE IF NOT EXISTS gestion_calificaciones;
USE gestion_calificaciones;

CREATE TABLE Grados (
    id_grado INT AUTO_INCREMENT PRIMARY KEY,
    nombre_grado VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE Alumnos (
    id_alumno INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(100),
    id_grado INT NOT NULL,
    FOREIGN KEY (id_grado) REFERENCES Grados(id_grado)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Asignaturas (
    id_asignatura INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    id_grado INT NOT NULL,
    FOREIGN KEY (id_grado) REFERENCES Grados(id_grado)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE Calificaciones (
    id_calificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_alumno INT NOT NULL,
    id_asignatura INT NOT NULL,
    calificacion DECIMAL(5,2) NOT NULL CHECK (calificacion BETWEEN 0 AND 100),
    
    FOREIGN KEY (id_alumno) REFERENCES Alumnos(id_alumno)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_asignatura) REFERENCES Asignaturas(id_asignatura)
        ON UPDATE CASCADE ON DELETE CASCADE,

    UNIQUE (id_alumno, id_asignatura)
);
