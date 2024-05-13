package com.educaccionit.rest.service;

import java.util.List;

import com.educaccionit.rest.model.Alumno;

public interface AlumnoService {
    List<Alumno> getAllAlumnos() throws Exception;
    Alumno getAlumnoById(int legajo) throws Exception;
    void addAlumno(Alumno alumno) throws Exception;
    void updateAlumno(int legajo, Alumno alumno) throws Exception;
    void deleteAlumno(int legajo) throws Exception;

}
