package com.educaccionit.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.educaccionit.rest.model.Alumno;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlumnoControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetAllAlumnos() {
		ResponseEntity<List> response = restTemplate.getForEntity("/alumnos", List.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetAlumnoById() {
		// Agregar un alumno con legajo = 1 antes de intentar obtenerlo por su id
		Alumno newAlumno = new Alumno(); // Crear un objeto Alumno con datos adecuados
		newAlumno.setLegajo(1);
		ResponseEntity<Void> addResponse = restTemplate.postForEntity("/alumnos", newAlumno, Void.class);
		assertEquals(HttpStatus.CREATED, addResponse.getStatusCode());

		// Obtener el alumno recién agregado por su id
		ResponseEntity<Alumno> response = restTemplate.getForEntity("/alumnos/1", Alumno.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void testAddAlumno() {
		Alumno newAlumno = new Alumno(); // Crear un objeto Alumno con datos adecuados
		ResponseEntity<Void> response = restTemplate.postForEntity("/alumnos", newAlumno, Void.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void testUpdateAlumno() {
		// Agregar un alumno con legajo = 1 antes de intentar actualizarlo
		Alumno newAlumno = new Alumno(); // Crear un objeto Alumno con datos adecuados
		newAlumno.setLegajo(1);
		ResponseEntity<Void> addResponse = restTemplate.postForEntity("/alumnos", newAlumno, Void.class);
		assertEquals(HttpStatus.CREATED, addResponse.getStatusCode());

		// JSON para actualizar el alumno con legajo = 1
		String updateJson = "{\"legajo\": 1, \"nombre\": \"Nombre del alumno\", \"edad\": 40}";

		// Configurar encabezados HTTP
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(updateJson, headers);

		// Actualizar el alumno con legajo = 1
		ResponseEntity<Void> updateResponse = restTemplate.exchange("/alumnos/{legajo}", HttpMethod.PUT, requestEntity,
				Void.class, 1);
		assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
	}

	@Test
	public void testDeleteAlumno() {
		int legajo = 1; // ID de un alumno existente en la base de datos
		ResponseEntity<Void> response = restTemplate.exchange("/alumnos/{legajo}", HttpMethod.DELETE, null, Void.class,
				legajo);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
