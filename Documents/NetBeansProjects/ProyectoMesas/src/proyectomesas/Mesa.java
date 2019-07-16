/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomesas;

import Crud.Conexion;
import java.sql.Connection;

/**
 *
 * @author josea
 */
public class Mesa 
{
    /**
     * Es el número de la mesa, llave primaria en la tabla
     */
    private String numero;
    
    /**
     * La capacidad que tiene la mesa, cuantas personas se pueden sentar.
     */
    private int capacidad;
    
    /**
     * Es el estado de la mesa, para saber si está o no ocupada.
     */
    private String estado;
    
    /**
     * 
     */
    private Connection conexion;
    
    /**
     * Constructor de nuevos objetos de la clase Mesa
     * @param numero
     * @param capacidad
     * @param estado 
     */
    public Mesa(String numero, int capacidad, String estado)
    {
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
        this.conexion = new Conexion().conectar();
    }

    public String getNumero() 
    {
        return numero;
    }

    public int getCapacidad()
    {
        return capacidad;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public void setCapacidad(int capacidad)
    {
        this.capacidad = capacidad;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }
    
    public String toString()
    {
        return "Mesa: numero=" + this.numero + "; Capacidad=" + this.capacidad + 
               "; Estado=" + this.estado ;
    }
    
 
}
