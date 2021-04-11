package com.example.OnValve.Modelo;

import java.io.Serializable;

public class Valvula implements Serializable
{
    private String nombre;
    private String fabricante;
    private String serial;
    private String correoElectronicoUsuario;
    private String ValvulaId;
    private int Estado;


    public Valvula(String Nombre, String Fabricante, String Serial, String Correo_Electronico_user, String valvulaId)
    {
        this.setNombre(Nombre);
        this.setFabricante(Fabricante);
        this.setSerial(Serial);
        this.setcorreoElectronicoUsuario(Correo_Electronico_user);
        this.setvalvulaId(valvulaId);
        this.setEstado(1);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    public String getcorreoElectronicoUsuario() {
        return correoElectronicoUsuario;
    }

    public void setcorreoElectronicoUsuario(String CorreoElectronicoUser)
    {
        this.correoElectronicoUsuario = CorreoElectronicoUser;
    }

    public String getvalvulaId() {
        return ValvulaId;
    }

    public void setvalvulaId(String valvulaId) {
        this.ValvulaId = valvulaId;
    }

    public int getEstado()
    {
        return Estado;
    }
    public void setEstado(int estado)
    {
        Estado = estado;
    }
}