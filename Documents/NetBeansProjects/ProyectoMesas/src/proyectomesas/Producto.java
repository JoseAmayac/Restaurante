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
public class Producto {
    /**
     * Es el número identificador de un producto, llave primaria de la tabla
     */
    private String id;
    
    /**
     * Nombre del producto
     */
    private String nombre;
    
    /**
     * Precio del producto 
     */
    private float precio;
    
    /**
     * Descripción del producto 
     */
    private String descripcion;
    
    /**
     * Es el tipo de plato que se sirve en el restaurante
     */
    private String tipo;
    /**
     * 
     */
    private Connection conexion;
    /**
     * Constructor de nuevos objetos de la clase Producto
     * @param id
     * @param nombre
     * @param precio
     * @param descripcion 
     * @param tipo 
     */
    public Producto(String id,String nombre, float precio, String descripcion,
                    String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.conexion = new Conexion().conectar();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
 
    @Override
    public String toString()
    {
        
        return this.nombre +"    "+ this.precio +"    "+ this.descripcion;
    }
    
    public String[] consultarInformación()
    {
        String informacion[]={this.nombre,this.descripcion,this.precio+""};
        return informacion;
    }
}
