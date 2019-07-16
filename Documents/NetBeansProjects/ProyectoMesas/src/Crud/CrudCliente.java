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
import proyectomesas.Cliente;
import proyectomesas.Persona;

/**
 *
 * @author Hector
 */
public class CrudCliente 
{
    private Cliente cliente;
    
    private Connection conexion;
    
    public CrudCliente(Cliente cliente)
    {
        this.cliente = cliente;
        this.conexion = new Conexion().conectar();
    }

    public int insertar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "INSERT INTO cliente VALUES(?,?,?,?,?,?)";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, cliente.getCedula());
            sentencia.setString(2, cliente.getNombres());
            sentencia.setString(3, cliente.getApellidos());
            sentencia.setString(4, cliente.getTelefono());
            sentencia.setString(5, cliente.getCorreo());
            sentencia.setString(6, cliente.getDireccion());
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
        String sql = "UPDATE cliente SET nombres = ?,apellidos = ?,telefono = ?,"
                    + " correo = ?,direccion =? WHERE cedula = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, cliente.getNombres());
            sentencia.setString(2, cliente.getApellidos());
            sentencia.setString(3, cliente.getTelefono());
            sentencia.setString(4, cliente.getCorreo());
            sentencia.setString(5, cliente.getDireccion());
            sentencia.setString(6, cliente.getCedula());
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    
    public Cliente consultar(String cedula)
    {
        PreparedStatement sentencia;
        Cliente cliente = null;
        
        String sql = "SELECT * FROM cliente WHERE cedula = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, cedula);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();

            cliente = new Cliente(resultado.getString("cedula"),resultado.getString("nombres"),
                                resultado.getString("apellidos"), resultado.getString("telefono"),
                                resultado.getString("correo"),resultado.getString("direccion"));
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return cliente;
    }
    
    public int eliminar(String cedula) 
    {
        PreparedStatement sentencia;
        int control = -1;
        
        String sql = "DELETE FROM cliente WHERE cedula = ?";
        
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
    
    public ArrayList<Persona> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<Persona> clientes = new ArrayList<>();
        
        String sql = "SELECT * FROM cliente";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                Cliente mesero = new Cliente(resultado.getString("cedula"),resultado.getString("nombres"),
                                resultado.getString("apellidos"), resultado.getString("telefono"),
                                resultado.getString("correo"),
                                resultado.getString("direccion"));
                clientes.add(mesero);
            } 
        }
        catch (SQLException ex)
        {
            //System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return clientes;
    }
}
