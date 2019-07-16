/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomesas;

/**
 *
 * @author Hector
 */
public class PedidoProducto {
    /**
     * 
     */
    private int id;
    
    /**
     * 
     */
    private Producto producto;
    
    /**
     * 
     */
    private Pedido pedido;
    
    /**
     * 
     */
    private String observaciones;

    public PedidoProducto(Producto producto, Pedido pedido, String observaciones) {
        this.producto = producto;
        this.pedido = pedido;
        this.observaciones = observaciones;
    }
    
    
    
    public int getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Override
    public String toString()
    {
        return "PedidoProducto: id=" + this.id + "; producto=" + this.producto.getId() + 
               "; pedido=" + this.pedido.getId() + "; Observaciones= " + this.observaciones;
    }
}
