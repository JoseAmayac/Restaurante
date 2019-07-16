/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import Crud.CrudCliente;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectomesas.Factura;

/**
 *
 * @author josea
 */
public class CrudFactura {
    
    
    private Factura factura;
    
    private Connection conexion;
    /**
     * Constructor de la clase Factura
     * @param fecha 
     * @param cliente 
     * @param mesero 
     */
    public CrudFactura(Factura factura) {
        this.factura = factura;
        //this.factura.getId();
        this.conexion = new Conexion().conectar();
    }
    
    public int insertar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "INSERT INTO factura(cliente_cedula,fecha,mesero_cedula,pedido_id) VALUES(?,?,?,?)";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, factura.getCliente().getCedula());
            sentencia.setTimestamp(2, factura.getFecha());
            sentencia.setString(3, factura.getPedido().getMesero().getCedula());
            
            sentencia.setInt(4, factura.getPedido().getId());
            control = sentencia.executeUpdate();
            
            int id = this.obtenerId();
            factura.setId(id);
        } 
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        
        
        return control;
    }
    
    public int modificar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "UPDATE factura SET cliente_cedula = ?, fecha = ?, "
                + "mesero_cedula = ?, pedido_id = ? WHERE id = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, factura.getCliente().getCedula());
            sentencia.setTimestamp(2, factura.getFecha());
            sentencia.setString(3, factura.getPedido().getMesero().getCedula());
            sentencia.setInt(4, factura.getPedido().getId());
            sentencia.setInt(5, factura.getId());
          
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public int eliminar(int id) 
    {
        PreparedStatement sentencia;
        int control = -1;
        
        String sql = "DELETE FROM factura WHERE id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, id);
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public Factura consultar(int id)
    {
        PreparedStatement sentencia;
        Factura factura = null;
        
        CrudCliente cliente = new CrudCliente(null);
        CrudPedido pedido = new CrudPedido(null);
        String sql = "SELECT * FROM factura WHERE id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,id);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();
            
            factura = new Factura(resultado.getTimestamp("fecha"),
                          cliente.consultar(resultado.getString("cliente_cedula")),
                          pedido.consultar(resultado.getInt("pedido_id")));
            
            factura.setId(resultado.getInt("id"));
            ////System.out.println(factura.getId());
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return factura;
    }

    public ArrayList<Factura> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<Factura> facturas = new ArrayList<>();
        
        String sql = "SELECT * FROM factura";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                CrudCliente cliente = new CrudCliente(null);
                CrudPedido pedido = new CrudPedido(null);
                Factura fac = new Factura(resultado.getTimestamp("fecha"),
                                     cliente.consultar(resultado.getString("cliente_cedula")),
                                     pedido.consultar(resultado.getInt("pedido_id")));
                facturas.add(fac);
            } 
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return facturas;
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


