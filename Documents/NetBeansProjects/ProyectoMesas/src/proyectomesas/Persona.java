/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomesas;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Hector
 */
public abstract class Persona
{
    /**
     * Cedula del cliente, es la llave primaria en la tabla.
     */
    protected String cedula;

    /**
     * Nombre del cliente
     */
    protected String nombres;

    /**
     * Apellido del cliente
     */
    protected String apellidos;

    /**
     * Telefono del cliente, campo opcional en la tabla
     */
    protected String telefono;

    /**
     * Correo del cliente, información de contacto
     */
    protected String correo;

    /**
     * Dirección del cliente
     */
    protected String direccion;

    
    /** 
     * @param cedula
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param correo
     * @param direccion 
     */

    public Persona(String cedula, String nombres, String apellidos, 
            String telefono, String correo, String direccion) 
    {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
