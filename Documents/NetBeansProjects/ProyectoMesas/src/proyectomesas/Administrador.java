/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomesas;

/**
 *
 * @author Hector
 */
public class Administrador extends Persona
{
    String contraseña;
    
    public Administrador(String cedula, String nombres, String apellidos, 
            String telefono, String correo, String direccion,String contraseña) {
        super(cedula, nombres, apellidos, telefono, correo, direccion);
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    @Override
    public String toString()
    {
        return "Administrador: Cedula=" + this.cedula + "; Nombres=" + this.nombres + 
               "; apellidos=" + this.apellidos + "; correro=" + this.correo;
    } 
    
}
