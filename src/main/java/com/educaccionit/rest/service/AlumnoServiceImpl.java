package com.educaccionit.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.educaccionit.rest.model.Alumno;
import com.educaccionit.rest.repository.AlumnoRepository;

@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService {
	
	@Autowired
	@Qualifier("alumnoRepository")
	private AlumnoRepository alumnoRepository;

	public AlumnoServiceImpl(AlumnoRepository alumnoRepository) throws Exception{
		this.alumnoRepository = alumnoRepository;
	}

	@Override
	public List<Alumno> getAllAlumnos() throws Exception {
		return alumnoRepository.findAll();
	}

	@Override
	public Alumno getAlumnoById(int legajo) throws Exception{
		return alumnoRepository.findById(legajo);
	}

	@Override
	public void addAlumno(Alumno alumno) throws Exception {
		alumnoRepository.save(alumno);
	}

	@Override
	public void updateAlumno(int legajo, Alumno alumno) throws Exception {
		Alumno existingAlumno = alumnoRepository.findById(legajo);
		if (existingAlumno != null) {
			existingAlumno.setNombre(alumno.getNombre());
			existingAlumno.setEdad(alumno.getEdad());
		}
	}

	@Override
	public void deleteAlumno(int legajo) throws Exception {
		alumnoRepository.delete(legajo);
	}
}
