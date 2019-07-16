/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectomesas.PedidoProducto;

/**
 *
 * @author Hector
 */
public class CrudPedidoProducto {
    /**
     * 
     */
    PedidoProducto proPed;
    /**
     * 
     */
    Connection conexion;

    public CrudPedidoProducto(PedidoProducto proPed) {
        this.proPed = proPed;
        
        this.conexion = new Conexion().conectar();
    }
    
    public int insertar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "INSERT INTO pedido_producto(producto_id,pedido_id,observaciones) VALUES(?,?,?)";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, proPed.getProducto().getId());
            sentencia.setInt(2, proPed.getPedido().getId());
            sentencia.setString(3, proPed.getObservaciones());
            control = sentencia.executeUpdate();
            
            int id = this.obtenerId();
            proPed.setId(id);
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public int modificar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "UPDATE pedido_producto SET producto_id = ?, pedido_id = ?, "
                + "observaciones = ? WHERE id = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, proPed.getProducto().getId());
            sentencia.setInt(2, proPed.getPedido().getId());
            sentencia.setString(3, proPed.getObservaciones());
            sentencia.setInt(4, proPed.getId());
          
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public int eliminar(int id,String producto_id,int pedido_id) 
    {
        PreparedStatement sentencia;
        int control = -1;
        
        String sql = "DELETE FROM pedido_producto WHERE id = ? AND producto_id =?"
                    + "AND pedido_id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, id);
            sentencia.setString(2, producto_id);
            sentencia.setInt(3, pedido_id);
    
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public PedidoProducto consultar(int id)
    {
        PreparedStatement sentencia;
        PedidoProducto pediPro = null;
        
        CrudProducto producto = new CrudProducto(null);
        CrudPedido pedido = new CrudPedido(null);
        String sql = "SELECT * FROM pedido_producto WHERE id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,id);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();
            
            pediPro = new PedidoProducto(producto.consultar(resultado.getString("producto_id")),
                          pedido.consultar(resultado.getInt("pedido_id")),
                          resultado.getString("observaciones"));
            
            pediPro.setId(resultado.getInt("id"));
            ////System.out.println(factura.getId());
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return pediPro;
    }
    public ArrayList<PedidoProducto> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<PedidoProducto> pediPros = new ArrayList<>();
        
        String sql = "SELECT * FROM pedido_producto";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                CrudProducto producto = new CrudProducto(null);
                CrudPedido pedido = new CrudPedido(null);
                PedidoProducto pedipro = new PedidoProducto(producto.consultar(resultado.getString("producto_id")),
                                     pedido.consultar(resultado.getInt("pedido_id")),
                                     resultado.getString("observaciones"));
                pediPros.add(pedipro);
            } 
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return pediPros;
    }
    
    private int obtenerId()
    {
        // Create the SQL
        Statement statement=null;
        try {
            statement = conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT LAST_INSERT_ID()";

        ResultSet rs = null;
        
        int id = -1;
        
        try 
        {
            // Run the SQL query on the database
            
            rs = statement.executeQuery(sql);
            
            rs.next();
            
            // Get the required value
            
            id = rs.getInt(1);
            
        } catch (SQLException ex) 
        {
            return -1;
        }

        return id;
    }
    
    public ArrayList<PedidoProducto> consultarTodosPedidos(int id)
    {
        PreparedStatement sentencia;
        
        ArrayList<PedidoProducto> pediPros = new ArrayList<>();
        
        String sql = "SELECT * FROM pedido_producto WHERE pedido_id=?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,id);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                CrudProducto producto = new CrudProducto(null);
                CrudPedido pedido = new CrudPedido(null);
                PedidoProducto pedipro = new PedidoProducto(producto.consultar(resultado.getString("producto_id")),
                                     pedido.consultar(resultado.getInt("pedido_id")),
                                     resultado.getString("observaciones"));
                
                pedipro.setId(resultado.getInt("id"));
                pediPros.add(pedipro);
            } 
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return pediPros;
    }
    
   
}
