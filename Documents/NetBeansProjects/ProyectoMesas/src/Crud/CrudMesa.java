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
import proyectomesas.Mesa;

/**
 *
 * @author josea
 */
public class CrudMesa 
{
   
    private Mesa mesa;
    
    /**
     * 
     */
    private Connection conexion;
    
    /**
     * Constructor de nuevos objetos de la clase Mesa
     * @param numero
     * @param capacidad
     * @param estado 
     */
    public CrudMesa(Mesa mesa)
    {
        this.mesa = mesa;
        this.conexion = new Conexion().conectar();
    }

    public int insertar()
    {
        PreparedStatement sentencia;
        int control = -1;
        String sql = "INSERT INTO mesa VALUES(?,?,?)";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, mesa.getNumero());
            sentencia.setInt(2, mesa.getCapacidad());
            sentencia.setString(3, mesa.getEstado());
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
        String sql = "UPDATE mesa SET capacidad = ?, estado = ? WHERE numero = ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, mesa.getCapacidad());
            sentencia.setString(2, mesa.getEstado());
            sentencia.setString(3, mesa.getNumero());
          
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public int eliminar(String numero) 
    {
        PreparedStatement sentencia;
        int control = -1;
        
        String sql = "DELETE FROM mesa WHERE numero = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, numero);
            control = sentencia.executeUpdate();
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return control;
    }
    
    public Mesa consultar(String numero)
    {
        PreparedStatement sentencia;
        Mesa mesa = null;
        
        String sql = "SELECT * FROM mesa WHERE numero = ?";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1,numero);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();

            mesa = new Mesa(resultado.getString("numero"),resultado.getInt("capacidad"),
                                resultado.getString("estado"));
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return mesa;
    }
    
    public ArrayList<Mesa> consultarTodos()
    {
        PreparedStatement sentencia;
        
        ArrayList<Mesa> mesas = new ArrayList<>();
        
        String sql = "SELECT * FROM mesa";
        
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            while(resultado.next())
            {
                Mesa mesa = new Mesa(resultado.getString("numero"),
                                resultado.getInt("capacidad"),
                                resultado.getString("estado"));
                mesas.add(mesa);
            } 
        }
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return mesas;
    }
}
