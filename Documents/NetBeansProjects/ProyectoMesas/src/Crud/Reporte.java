/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import proyectomesas.Cliente;

/**
 *
 * @author Hector
 */
public class Reporte
{
    Connection conexion;

    public Reporte()
    {
        this.conexion = new Conexion().conectar();
    }
    
    public int consultarVentasTotales(Date fecha1,Date fecha2)
    {
        PreparedStatement sentencia;
        int resultadoConsulta = -1;
        String sql = "SELECT COUNT(*) AS resultado FROM factura WHERE fecha BETWEEN ? AND ?";
     
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setDate(1,fecha1);
            sentencia.setDate(2,fecha2);
            ResultSet resultado = sentencia.executeQuery();
            resultado.next();
            resultadoConsulta = resultado.getInt("resultado");
            
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return resultadoConsulta;
    }
    
    public ArrayList<String[]> consultarVentasMesas(Date fecha1,Date fecha2)
    {
        PreparedStatement sentencia;
        ArrayList<String[]> resultados = new ArrayList<>();
        String sql = "SELECT mesa_numero,COUNT(*) AS res FROM pedido WHERE (fecha_pedido BETWEEN ? AND ?) GROUP BY mesa_numero;";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setDate(1,fecha1);
            sentencia.setDate(2,fecha2);
            ResultSet resultado = sentencia.executeQuery();
 
            while(resultado.next())
            {
                String[] datos = {resultado.getString("mesa_numero"),resultado.getString("res")};
                resultados.add(datos);
            } 
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return resultados;
    }
    
    public ArrayList<String[]> consultarTop(Date fecha1,Date fecha2)
    {
        PreparedStatement sentencia;
        ArrayList<String[]> resultados = new ArrayList<>();
        String sql = "SELECT COUNT(*) AS cant, producto_id FROM pedido INNER JOIN "
                    + "pedido_producto ON pedido.id=pedido_producto.pedido_id "
                    + "WHERE fecha_pedido BETWEEN ? AND ? GROUP BY producto_id "
                    + "ORDER BY cant desc LIMIT 0,10" ;
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setDate(1,fecha1);
            sentencia.setDate(2,fecha2);
            ResultSet resultado = sentencia.executeQuery();
 
            CrudProducto controlador = new CrudProducto(null);
            while(resultado.next())
            {
                String[] datos = {resultado.getString("cant"),controlador.consultar(resultado.getString("producto_id")).getNombre()};
                resultados.add(datos);
            } 
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return resultados;
    }
    
    public ArrayList<Cliente> consultarClientesAusentes(Date fecha1,Date fecha2)
    {
        PreparedStatement sentencia;
        Cliente cliente=null;
        ArrayList<Cliente> resultados = new ArrayList<>();
        String sql = "SELECT cedula, nombres, apellidos,telefono "
                     + "FROM factura INNER JOIN cliente "
                     + "ON cliente.cedula = factura.cliente_cedula"
                     + " WHERE fecha NOT BETWEEN ? AND ?";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setDate(1,fecha1);
            sentencia.setDate(2,fecha2);
            ResultSet resultado = sentencia.executeQuery();
 
            while(resultado.next())
            {
                cliente = new Cliente(resultado.getString("cedula"),resultado.getString("nombres") 
                                      ,resultado.getString("apellidos"),resultado.getString("telefono"), "", "");
                
                resultados.add(cliente);
            } 
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return resultados;
    }
    
    public ArrayList<String[]> consultarComprasClientes(Date fecha1,Date fecha2)
    {
        PreparedStatement sentencia;
        Cliente cliente=null;
        ArrayList<String[]> resultados = new ArrayList<>();
        String sql = "SELECT cedula,res2.nombre_completo,SUM(precio) AS precio"
                + " FROM (SELECT cedula,res1.nombre_completo,pedido_producto.producto_id "
                + "FROM (SELECT cedula,CONCAT(nombres,' ',apellidos) "
                + "AS nombre_completo,pedido_id FROM factura INNER JOIN cliente " 
                + "ON cliente.cedula = factura.cliente_cedula INNER JOIN pedido "
                + "ON factura.pedido_id=pedido.id WHERE fecha BETWEEN ? AND ?) "
                + "AS res1 INNER JOIN pedido_producto " 
                + "ON res1.pedido_id = pedido_producto.pedido_id)"
                + " AS res2 INNER JOIN producto " +
                "ON res2.producto_id=producto.id GROUP BY cedula";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setDate(1,fecha1);
            sentencia.setDate(2,fecha2);
            ResultSet resultado = sentencia.executeQuery();
 
            while(resultado.next())
            {
                String[] datos = {resultado.getString("cedula"),
                                resultado.getString("nombre_completo"),
                                resultado.getString("precio")};
                
                resultados.add(datos);
            } 
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return resultados;
    }
    
    public ArrayList<String[]> consultarReporte(int id)
    {
        PreparedStatement sentencia;
        ArrayList<String[]> resultados = new ArrayList<>();
        String sql = "SELECT res2.cant,res2.precio_total,tipo.nombre "
                    + "FROM (SELECT COUNT(*) AS cant,SUM(producto.precio) "
                    + "AS precio_total,producto.tipo_id "
                    + "FROM (SELECT producto_id FROM factura INNER JOIN "
                    + "pedido_producto ON pedido_producto.pedido_id=factura.pedido_id"
                    + " WHERE factura.id= ?) AS res INNER JOIN producto " 
                    + "ON res.producto_id=producto.id GROUP by producto.tipo_id) "
                    + "AS res2 INNER JOIN tipo ON res2.tipo_id=tipo.id;";
        try 
        {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1,id);
            ResultSet resultado = sentencia.executeQuery();
 
            while(resultado.next())
            {
                String[] datos = {resultado.getString("nombre"),
                                resultado.getString("cant"),
                                resultado.getString("precio_total")};
                
                resultados.add(datos);
            } 
        } 
        catch (SQLException ex)
        {
            System.err.println("ERROR: No se pudo ejecutar: " + sql);
        }
        return resultados;
    } 
}
