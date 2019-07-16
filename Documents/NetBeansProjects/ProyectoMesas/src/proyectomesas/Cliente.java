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
public class Cliente extends Persona
{

    public Cliente(String cedula,String nombres,String apellidos,String telefono,
            String correo, String direccion)
    {
        super(cedula,nombres,apellidos,telefono,correo,direccion);
    }
    
    public String toString()
    {
        return "Cliente: Cedula=" + this.cedula + "; Nombres=" + this.nombres + 
               "; apellidos=" + this.apellidos + "; correro=" + this.correo;
    }
    
    public String[] consultarInformación()
    {
        String informacion[]={this.getCedula(),this.getNombres()+" "+this.getApellidos(),this.getTelefono()};
        return informacion;
    }
}
