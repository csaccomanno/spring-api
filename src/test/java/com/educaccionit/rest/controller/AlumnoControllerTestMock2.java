package com.educaccionit.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.educaccionit.rest.model.Alumno;
import com.educaccionit.rest.service.AlumnoService;

//@ExtendWith(MockitoExtension.class)
public class AlumnoControllerTestMock2 {

	@Mock
    private AlumnoService alumnoServiceMock;

    @InjectMocks
    private AlumnoController alumnoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAlumnos() throws Exception {
        // Mock de la lista de alumnos
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno(1, "Juan", 20));
        alumnos.add(new Alumno(2, "Maria", 22));

        // Mock del servicio
        when(alumnoServiceMock.getAllAlumnos()).thenReturn(alumnos);

        // Llamada al método del controlador
        ResponseEntity<List<Alumno>> response = alumnoController.getAllAlumnos();

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alumnos, response.getBody());
    }

    @Test
    public void testGetAlumnoById() throws Exception {
        // Mock de un alumno
        Alumno alumno = new Alumno(1, "Juan", 20);

        // Mock del servicio
        when(alumnoServiceMock.getAlumnoById(1)).thenReturn(alumno);
        when(alumnoServiceMock.getAlumnoById(2)).thenReturn(null);

        // Llamada al método del controlador
        ResponseEntity<Alumno> response1 = alumnoController.getAlumnoById(1);
        ResponseEntity<Alumno> response2 = alumnoController.getAlumnoById(2);

        // Verificación
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(alumno, response1.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        assertNull(response2.getBody());
    }

    @Test
    public void testAddAlumno() throws Exception {
        // Mock de un alumno
        Alumno alumno = new Alumno(1, "Juan", 20);

        // Llamada al método del controlador
        ResponseEntity<Void> response = alumnoController.addAlumno(alumno);

        // Verificación
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(alumnoServiceMock, times(1)).addAlumno(alumno);
    }

    @Test
    public void testUpdateAlumno() throws Exception {
        // Mock de un alumno
        Alumno alumno = new Alumno(1, "Juan", 20);

        // Llamada al método del controlador
        ResponseEntity<Void> response = alumnoController.updateAlumno(1, alumno);

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(alumnoServiceMock, times(1)).updateAlumno(1, alumno);
    }

    @Test
    public void testDeleteAlumno() throws Exception {
        // Llamada al método del controlador
        ResponseEntity<Void> response = alumnoController.deleteAlumno(1);

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(alumnoServiceMock, times(1)).deleteAlumno(1);
    }
}
