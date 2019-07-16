/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Crud.CrudMesero;
import Crud.CrudFactura;
import Crud.CrudMesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectomesas.Pedido;

/**
 *
 * @author josea
 */
public class CrudPedido 
{
  
    private Pedido pedido;
    
    /**
     * 
     */
    Connection conexion;
    /**
     * 
     * @param pedido 
     */
    public CrudPedido(Pedido pedido) 
    {
        this.pedido=pedido;
        this.conexion = new Conexion().conectar();
    }


    public int insertar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "INSERT INTO pedido VALUES(?,?,?,?)";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, pedido.getId());
            sentencia.setString(2, pedido.getMesa().getNumero());
            sentencia.setTimestamp(3, pedido.getFecha());
            sentencia.setString(4, pedido.getMesero().getCedula());
            control = sentencia.executeUpdate();
            
            int id = this.obtenerId();
            pedido.setId(id);
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
        String sql = "UPDATE pedido SET mesa_numero = ?,"
                + "fecha_pedido = ?, mesero_cedula=? WHERE id = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, pedido.getMesa().getNumero());
            sentencia.setTimestamp(2, pedido.getFecha());
            sentencia.setString(3, pedido.getMesero().getCedula());
            sentencia.setInt(4, pedido.getId());
            
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public int eliminar(int idP) 
    {
        PreparedStatement sentencia;
        int control = -1;
        
        String sql = "DELETE FROM pedido WHERE id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idP);
            control = sentencia.executeUpdate();
            
            
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    /**
     * 
     * @return 
     */
    public Pedido consultar(int n)
    {
        PreparedStatement sentencia;
        Pedido pedido = null;
        CrudMesero mesero = new CrudMesero(null);
        CrudMesa mesa = new CrudMesa(null);
        String sql = "SELECT * FROM pedido WHERE id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,n);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();
            
            pedido = new Pedido(mesa.consultar(resultado.getString("mesa_numero")),
                          resultado.getTimestamp("fecha_pedido"),
                          mesero.consultar(resultado.getString("mesero_cedula")));
            
            pedido.setId(resultado.getInt("id"));
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return pedido;
    }
    
    public int consultarMaximo(String n)
    {
        PreparedStatement sentencia;
        int id = -1;

        String sql = "SELECT MAX(id) AS res FROM pedido WHERE mesa_numero = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1,n);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();
            id = resultado.getInt("res");
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return id;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Pedido> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        String sql = "SELECT * FROM pedido";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                CrudMesero mesero = new CrudMesero(null);
                CrudMesa mesa = new CrudMesa(null);
                Pedido fac = new Pedido(mesa.consultar(resultado.getString("mesa_numero")),
                                     resultado.getTimestamp("fecha_pedido"),
                                     mesero.consultar(resultado.getString("mesero_cedula")));
                pedidos.add(fac);
            } 
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return pedidos;
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
    
}

