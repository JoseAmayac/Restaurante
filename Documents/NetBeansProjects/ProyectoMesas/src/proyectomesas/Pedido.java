/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomesas;

import Crud.Conexion;
import java.sql.Connection;
import java.sql.Timestamp;

/**
 *
 * @author josea
 */
public class Pedido {
    /**
     * El número de la mesa en donde se hizo el pedido
     */
    Mesa mesa;
    
    /**
     * Fecha en la que se hizo el pedido
     */
    private Timestamp fecha;
    
    /**
     * El mesero que atendio el pedido
     */
    Mesero mesero;
    
    /**
     * 
     */
    private int id;
    /**
     * 
     */
    Connection conexion;
    /**
     * 
     * @param mesaNumero
     * @param mesero
     * @param fecha 
     */
    public Pedido(Mesa mesa,Timestamp fecha, 
            Mesero mesero) {
        this.mesa = mesa;
        this.mesero = mesero;
        this.fecha = fecha;
        this.conexion = new Conexion().conectar();
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

   

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Mesero getMesero() {
        return mesero;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString()
    {
        return "Pedido: id=" + this.id + "; Mesa=" + this.mesa.getNumero() + 
               "; Fecha=" + this.fecha + "; mesero= " + this.mesero.getCedula();
    }
    
}

