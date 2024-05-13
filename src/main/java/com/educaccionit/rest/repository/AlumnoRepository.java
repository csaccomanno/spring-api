package com.educaccionit.rest.repository;

import java.util.List;

import com.educaccionit.rest.model.Alumno;

public interface AlumnoRepository {
    List<Alumno> findAll();
    Alumno findById(int legajo);
    void save(Alumno alumno);
    void delete(int legajo);

}
