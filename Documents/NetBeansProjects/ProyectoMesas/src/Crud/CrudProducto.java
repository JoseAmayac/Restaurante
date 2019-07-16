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
import java.util.ArrayList;
import proyectomesas.Producto;

/**
 *
 * @author josea
 */
public class CrudProducto 
{

    private Producto producto;
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
    public CrudProducto(Producto producto) 
    {

        this.producto=producto;
        this.conexion = new Conexion().conectar();
    }

    
    /**
     * 
     * @return 
     */
    public int insertar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "INSERT INTO producto VALUES(?,?,?,?,?)";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, producto.getId());
            sentencia.setString(2, producto.getNombre());
            sentencia.setFloat(3, producto.getPrecio());
            sentencia.setString(4, producto.getDescripcion());
            sentencia.setString(5, producto.getTipo());
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
     * @param id
     * @return 
     */
    public int modificar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "UPDATE producto SET nombre = ?, precio = ?, descripcion = ?,"
                    + "tipo_id = ? WHERE id = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, producto.getNombre());
            sentencia.setFloat(2, producto.getPrecio());
            sentencia.setString(3, producto.getDescripcion());
            sentencia.setString(4, producto.getTipo());
            sentencia.setString(5, producto.getId());
          
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public int eliminar(String id) 
    {
        PreparedStatement sentencia;
        int control = -1;
        
        String sql = "DELETE FROM producto WHERE id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, id);
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public Producto consultar(String id)
    {
        PreparedStatement sentencia;
        Producto producto = null;
        
        String sql = "SELECT * FROM producto WHERE id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1,id);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();

            producto = new Producto(resultado.getString("id"),resultado.getString("nombre"),
                                    resultado.getFloat("precio"),resultado.getString("descripcion"),
                                    resultado.getString("tipo_id"));
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return producto;
    }
    
    public ArrayList<Producto> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<Producto> productos = new ArrayList<>();
        
        String sql = "SELECT * FROM producto";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                Producto producto = new Producto(resultado.getString("id"),
                                     resultado.getString("nombre"),
                                     resultado.getFloat("precio"),
                                     resultado.getString("descripcion"),
                                     resultado.getString("tipo_id"));
                productos.add(producto);
            } 
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return productos;
    }
    
}
