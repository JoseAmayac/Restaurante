/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hector
 */
public class Conexion 
{
    protected Connection conexion;
    
    private String url;
    
    private String usuario;
    
    private String contraseña;

    
    public Conexion() 
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();   
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
        {
            System.err.println("ERROR! no fue posible cargar el driver JDBC: " + ex.getLocalizedMessage());
            System.exit(1);
        } 
    }
    
    public Connection conectar()
    {
        url= "jdbc:mysql://localhost/restaurante";
        usuario="proyecto";
        contraseña = "123456"; 
        
        try
        {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        }
        catch(SQLException e)
        {
            System.err.println("ERROR Conectandome: " + e.getMessage());
            System.exit(1);
        }
        return conexion;
    } 
}
