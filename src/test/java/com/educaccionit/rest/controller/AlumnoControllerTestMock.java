package com.educaccionit.rest.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.educaccionit.rest.model.Alumno;
import com.educaccionit.rest.service.AlumnoService;

@ExtendWith(MockitoExtension.class)
public class AlumnoControllerTestMock {

    private MockMvc mockMvc;

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    @BeforeEach
    public void setUp() {
    	/*
MockMvcBuilders: Es una clase proporcionada por Spring MVC Test que permite construir instancias de MockMvc 
para probar controladores.

standaloneSetup(alumnoController): Este método configura MockMvc para trabajar con el controlador 

AlumnoController. Le dice a MockMvc que solo debe cargar el controlador proporcionado, sin cargar ninguna otra configuración de Spring. Esto significa que el contexto de la aplicación real no se carga; 
solo se carga el controlador que se pasa como argumento.

build(): Este método finaliza la configuración y construye una instancia de MockMvc lista para ser utilizada en los tests. 
    	 */
        mockMvc = MockMvcBuilders.standaloneSetup(alumnoController).build();
    }

    @Test
    public void testGetAllAlumnos() throws Exception {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno(1, "Juan", 20));
        alumnos.add(new Alumno(2, "Maria", 22));

        when(alumnoService.getAllAlumnos()).thenReturn(alumnos);

        mockMvc.perform(get("/alumnos"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].legajo").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Juan"))
            .andExpect(jsonPath("$[0].edad").value(20))
            .andExpect(jsonPath("$[1].legajo").value(2))
            .andExpect(jsonPath("$[1].nombre", notNullValue()))
            .andExpect(jsonPath("$[1].nombre").value("Maria"))
            .andExpect(jsonPath("$[1].edad").value(22));
        System.out.println("Yo");
    }

    @Test
    public void testGetAlumnoById() throws Exception {
        Alumno alumno = new Alumno(1, "Juan", 20);
        when(alumnoService.getAlumnoById(1)).thenReturn(alumno);
        mockMvc.perform(get("/alumnos/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddAlumno() throws Exception {
        Alumno alumno = new Alumno(1, "Juan", 20);
        mockMvc.perform(post("/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"legajo\":1,\"nombre\":\"Juan\",\"edad\":20}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateAlumno() throws Exception {
        Alumno alumno = new Alumno(1, "Juan", 20);
        mockMvc.perform(put("/alumnos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"legajo\":1,\"nombre\":\"Juan\",\"edad\":20}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAlumno() throws Exception {
        mockMvc.perform(delete("/alumnos/1"))
                .andExpect(status().isOk());
    }

}
