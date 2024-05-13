package com.educaccionit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@ConfigurationProperties(prefix = "clave")
public class Configuracion {
	
	@Value("${config1.nombre1}")
	private String nombre1;
	
	@Value("${config1.nombre2}")
	private String nombre2;
	
	
	private String propiedad1;
	private String propiedad2;

	
	public Configuracion() {
		super();
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getPropiedad1() {
		return propiedad1;
	}
	public void setPropiedad1(String propiedad1) {
		this.propiedad1 = propiedad1;
	}
	public String getPropiedad2() {
		return propiedad2;
	}
	public void setPropiedad2(String propiedad2) {
		this.propiedad2 = propiedad2;
	}
	

}


/*
config1.nombre1=este es el valor
config1.nombre2=este es el valor

clave.propiedad1=23
clave.propiedad2=23
*/