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
import proyectomesas.Tipo;

/**
 *
 * @author Hector
 */
public class CrudTipo {
    Tipo tipo;
    
    Connection conexion;

    public CrudTipo(Tipo tipo) {
        this.tipo = tipo;
        this.conexion = new Conexion().conectar();
    }
    
    
    public int insertar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "INSERT INTO tipo VALUES(?,?)";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, tipo.getId());
            sentencia.setString(2, tipo.getNombre());
            control = sentencia.executeUpdate();
            
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
        String sql = "UPDATE tipo SET nombre = ? WHERE id = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, tipo.getId());
          
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
        
        String sql = "DELETE FROM tipo WHERE id = ?";
        
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
    
    public Tipo consultar(int id)
    {
        PreparedStatement sentencia;
        Tipo tipo = null;
        String sql = "SELECT * FROM tipo WHERE id = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,id);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();
            
            tipo = new Tipo(resultado.getString("nombre"),resultado.getString("id"));
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return tipo;
    }
    
    public ArrayList<Tipo> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<Tipo> tipos = new ArrayList<>();
        
        String sql = "SELECT * FROM tipo";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                Tipo tipo = new Tipo(resultado.getString("nombre"),
                                     resultado.getString("id"));
                                     
                tipos.add(tipo);
            } 
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return tipos;
    }
}
