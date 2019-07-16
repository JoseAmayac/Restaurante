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
import proyectomesas.Mesero;
import proyectomesas.Persona;


/**
 *
 * @author josea
 */
public class CrudMesero 
{

    private Mesero mesero;
    
    private Connection conexion;

    public CrudMesero(Mesero mesero)
    {
        this.mesero = mesero;
        this.conexion = new Conexion().conectar();
    }

    public int insertar() 
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "INSERT INTO mesero VALUES(?,?,?,?,?,?,?,?)";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, mesero.getCedula());
            sentencia.setString(2, mesero.getNombres());
            sentencia.setString(3, mesero.getApellidos());
            sentencia.setString(4, mesero.getTelefono());
            sentencia.setString(5, mesero.getCorreo());
            sentencia.setString(6, mesero.getContraseña());
            sentencia.setString(7, mesero.getDireccion());
            sentencia.setString(8, mesero.getObservaciones());
            control = sentencia.executeUpdate();
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
        String sql = "UPDATE mesero SET nombres = ?,apellidos = ?,telefono = ?,"
                    + " correo = ?,contrasena =?,direccion =? ,observaciones=?"
                    + " WHERE cedula = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, mesero.getNombres());
            sentencia.setString(2, mesero.getApellidos());
            sentencia.setString(3, mesero.getTelefono());
            sentencia.setString(4, mesero.getCorreo());
            sentencia.setString(5, mesero.getContraseña());
            sentencia.setString(6, mesero.getDireccion());
            sentencia.setString(7, mesero.getObservaciones());
            sentencia.setString(8, mesero.getCedula());
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    
    public int eliminar(String cedula) 
    {
        PreparedStatement sentencia;
        int control = -1;
        
        String sql = "DELETE FROM mesero WHERE cedula = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, cedula);
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }

  
    public Mesero consultar(String cedula)
    {
        PreparedStatement sentencia;
        Mesero mesero = null;
        
        String sql = "SELECT * FROM mesero WHERE cedula = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, cedula);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();

            mesero = new Mesero(resultado.getString("cedula"),resultado.getString("nombres"),
                                resultado.getString("apellidos"), resultado.getString("telefono"),
                                resultado.getString("correo"),resultado.getString("contrasena"),
                                resultado.getString("direccion"),resultado.getString("observaciones"));
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return mesero;
    }

  
    public ArrayList<Persona> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<Persona> meseros = new ArrayList<>();
        
        String sql = "SELECT * FROM mesero";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                Mesero mesero = new Mesero(resultado.getString("cedula"),resultado.getString("nombres"),
                                resultado.getString("apellidos"), resultado.getString("telefono"),
                                resultado.getString("correo"),resultado.getString("contrasena"),
                                resultado.getString("direccion"),resultado.getString("observaciones"));
                meseros.add(mesero);
            } 
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return meseros;
    }
    
    public Mesero consultarLogin(String correo)
    {
        PreparedStatement sentencia;
        Mesero mesero = null;
        
        String sql = "SELECT * FROM mesero WHERE correo = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, correo);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();

            mesero = new Mesero(resultado.getString("cedula"),resultado.getString("nombres"),
                                resultado.getString("apellidos"), resultado.getString("telefono"),
                                resultado.getString("correo"),resultado.getString("contrasena"),
                                resultado.getString("direccion"),resultado.getString("observaciones"));
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return mesero;
    }
}