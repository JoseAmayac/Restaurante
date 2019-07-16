/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomesas;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author josea
 */
public class Factura {
    /**
     * Id de la factura, número auto numerico y llave primaria en la tabla
     */
    private int id;
    
    /**
     * Representa el día en el que se hizo la factura
     */
    private Timestamp fecha;
    
    private Pedido pedido;
    
    private Cliente cliente;
    

    /**
     * Constructor de la clase Factura
     * @param fecha 
     * @param cliente 
     * @param pedido 
     * @param mesero 
     */
    public Factura(Timestamp fecha,Cliente cliente,Pedido pedido) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.pedido = pedido;
    }
    
    public int getId() {
        return id;
    }
    
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    


    public void setId (int id) {
        this.id = id;
    }

 

    public Pedido getPedido() {
        return pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    
    
    
    @Override
    public String toString()
    {
        return "Factura: id=" + this.id + "; Fecha=" + this.fecha + 
               "; Cliente=" + this.cliente.getCedula() + "; Pedido= " + this.pedido.getId() +
                "; Mesero=" + this.pedido.getMesero().getCedula();
    }
}


