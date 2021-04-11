package com.example.OnValve.Modelo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Usuario
{
    private String nombre;
    private String apellido;
    private String ciudad;
    private String correoElectronico;
    private String contraseña;
    private List<Valvula> valvulas;

    public Usuario(String nombre, String apellido, String ciudad, String correoElectronico, String contraseña)
    {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCiudad(ciudad);
        this.setCorreoElectronico(correoElectronico);
        this.setContraseña(contraseña);
        this.valvulas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Valvula> getValvulas(){return valvulas;}

    public void setValvulas(Valvula valvula){this.valvulas.add(valvula);}

}

