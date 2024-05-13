package com.educaccionit.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educaccionit.rest.model.Alumno;
import com.educaccionit.rest.service.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	
	@Autowired
	@Qualifier("alumnoService")
    private AlumnoService alumnoService;

	private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumnos() throws Exception {
        List<Alumno> alumnos = alumnoService.getAllAlumnos();
        logger.info("Consulta de Todos los alumnos");
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable("legajo") int legajo) throws Exception {
        Alumno alumno = alumnoService.getAlumnoById(legajo);
        logger.info("Consulta de alumno por Id {}", legajo);
        if (alumno != null) {
            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addAlumno(@RequestBody Alumno alumno) throws Exception {
        alumnoService.addAlumno(alumno);
        logger.info("Alta de alumno {}", alumno);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{legajo}")
    public ResponseEntity<Void> updateAlumno(@PathVariable("legajo") int legajo, @RequestBody Alumno alumno) throws Exception {
        alumnoService.updateAlumno(legajo, alumno);
        logger.info("Actualizacion de alumno con lejago {}, Alumno: {}", legajo, alumno);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{legajo}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable("legajo") int legajo) throws Exception {
        alumnoService.deleteAlumno(legajo);
        logger.info("Baja de alumno con Legajo: {}", legajo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
