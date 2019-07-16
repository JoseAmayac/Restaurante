/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomesas;


/**
 *
 * @author josea
 */
public class Mesero extends Persona
{
    /**
     * Contraseña que usa junto al correo para autenticarse
     */
    private String contraseña;

    /**
     * Observaciones que se le pueden dar al mesero, campo opcional en la tabla.
     */
    private String observaciones;
    
    /**
     * Constructor de la clase Mesero
     * @param cedula
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param correo
     * @param contraseña
     * @param direccion
     * @param observaciones 
     */
    public Mesero(String cedula, String nombres, String apellidos,
            String telefono, String correo, String contraseña, String direccion,
            String observaciones)
    {
        
        super(cedula,nombres,apellidos,telefono,correo,direccion);
        this.contraseña = contraseña;
        this.observaciones = observaciones;
    }

    public String getContraseña()
    {
        return contraseña;
    }

    public String getObservaciones()
    {
        return observaciones;
    }
    

    public void setContraseña(String contraseña)
    {
        this.contraseña = contraseña;
    }

    public void setObservaciones(String observaciones)
    {
        this.observaciones = observaciones;
    }
 

    @Override
    public String toString()
    {
        return "Mesero: Cedula=" + this.cedula + "; Nombres=" + this.nombres + 
               "; apellidos=" + this.apellidos + "; correro=" + this.correo;
    }
    
}
