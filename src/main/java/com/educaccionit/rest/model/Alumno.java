package com.educaccionit.rest.model;

public class Alumno {
    private int legajo;
    private String nombre;
    private int edad;
    
    
	public Alumno(int legajo, String nombre, int edad) {
		super();
		this.legajo = legajo;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Alumno() {
		super();
	}

	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Alumno [legajo=" + legajo + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
    
}
