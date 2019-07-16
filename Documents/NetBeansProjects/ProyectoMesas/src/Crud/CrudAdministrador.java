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
import proyectomesas.Administrador;
import proyectomesas.Persona;

/**
 *
 * @author Hector
 */
public class CrudAdministrador {
    Administrador administrador;
    
    Connection conexion;

    public CrudAdministrador(Administrador admin)
    {
        this.administrador = admin;
        this.conexion = new Conexion().conectar();
    }
    
    
    public int modificar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "UPDATE administrador SET nombres = ?,apellidos = ?,telefono = ?,"
                    + " correo = ?,contrasena =?,direccion =? WHERE cedula = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, administrador.getNombres());
            sentencia.setString(2, administrador.getApellidos());
            sentencia.setString(3, administrador.getTelefono());
            sentencia.setString(4, administrador.getCorreo());
            sentencia.setString(5, administrador.getContraseña());
            sentencia.setString(6, administrador.getDireccion());
            sentencia.setString(7, administrador.getCedula());
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
        
        String sql = "DELETE FROM administrador WHERE cedula = ?";
        
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
    
    public Administrador consultar(String cedula)
    {
        PreparedStatement sentencia;
        Administrador administrador = null;
        
        String sql = "SELECT * FROM administrador WHERE cedula = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, cedula);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();

            administrador = new Administrador(resultado.getString("cedula"),resultado.getString("nombres"),
                                resultado.getString("apellidos"), resultado.getString("telefono"),
                                resultado.getString("correo"),resultado.getString("direccion"),
                                resultado.getString("contrasena"));
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        
        return administrador;
    }
    
    public ArrayList<Persona> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<Persona> administradores = new ArrayList<>();
        
        String sql = "SELECT * FROM administrador";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                Administrador administrador = new Administrador(resultado.getString("cedula"),
                                resultado.getString("nombres"),resultado.getString("apellidos"), 
                                resultado.getString("telefono"),resultado.getString("correo"),
                                resultado.getString("direccion"),resultado.getString("contrasena"));
                administradores.add(administrador);
            } 
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return administradores;
    }
    
    public Administrador consultarLogin(String correo)
    {
        PreparedStatement sentencia;
        Administrador administrador = null;
        
        String sql = "SELECT * FROM administrador WHERE correo = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, correo);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();

            administrador = new Administrador(resultado.getString("cedula"),resultado.getString("nombres"),
                                resultado.getString("apellidos"), resultado.getString("telefono"),
                                resultado.getString("correo"), resultado.getString("direccion")
                                ,resultado.getString("contrasena"));
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return administrador;
    }
}
