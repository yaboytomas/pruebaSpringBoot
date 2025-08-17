package com.ipss.demo.model;

// para autogenerar automaticamente la idenitdad
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// para indicar que esta clase es una entidad de base de datos
@Entity
public class Usuario {
    
    // atributos
    @Id // identificador unico
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para autogenerar automaticamente la idenitdad
    private Long id;
    private String nombre;
    private String contrasena;
    private String rol; //  "cliente" / "administrador"

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
