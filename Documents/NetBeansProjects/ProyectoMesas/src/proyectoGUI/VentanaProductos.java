/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import Crud.CrudPedidoProducto;
import Crud.CrudProducto;
import proyectomesas.Mesa;
import proyectomesas.Mesero;
import proyectomesas.Pedido;
import proyectomesas.PedidoProducto;
import proyectomesas.Producto;

/**
 *
 * @author Hector
 */
public class VentanaProductos extends javax.swing.JDialog implements ActionListener,AdjustmentListener{

    JScrollPane scroll;
    JPanel panel;
    Producto producto;
    int pos=10;
    int pos2=10;
    ArrayList<JButton> b;
    Mesa mesa;
    Mesero mesero;
    Pedido pedido;
    ArrayList<Producto> productos;
    boolean pulsador;
    int contador;
    ArrayList<PedidoProducto> nuevosProductos;
    /**
     * Creates new form VentanaProductos
     */
    public VentanaProductos(java.awt.Frame parent, boolean modal,Pedido pedido)
    {
        super(parent, modal);
        initComponents();
        this.pedido=pedido;
        b= new ArrayList<>();
        this.pintarProductos();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Menú");
        this.pulsador=false;
        this.contador=0;
        this.nuevosProductos=new ArrayList<>();
    }


    public void pintarProductos()
    {        
       producto = new Producto("", "", 0, "", "");
       CrudProducto productoCrud = new CrudProducto(producto);
       productos = productoCrud.consultarTodos();

        Container c = this.getContentPane();
        panel = new JPanel();
        //panel.setBackground(Color.yellow);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setSize(this.getWidth(), this.getHeight());
        
        for (int i = 0; i < productos.size(); i++) 
        {
            Producto p = productos.get(i);
            JPanel panel1 = new JPanel();
            //panel1.setBackground(Color.red);
            panel1.setSize(this.getWidth()-140,100);
            panel1.setLocation(10, pos2);
            pos2=110+pos2;
            panel1.setLayout(null);
            
            String nombrebtn = i+1+"";
            String descripcion = p.getDescripcion();
            String inf = "<html>Nombre: "+p.getNombre()+"<br>" + "Precio: "+p.getPrecio() +"<br>"+ "Descripcion: "+ this.descripcion(descripcion)+"<html>";

            
            JButton b =new JButton();
            b.setName("Seleccionar");
            b.setLocation(0, 0);
            b.setSize((this.getWidth()-140)/2,100);
            b.addActionListener(this);
            b.setBackground(Color.cyan);
            b.setLayout(null);
            b.setText(inf);
            b.setFocusable(false);
            b.setHorizontalAlignment(SwingConstants.LEFT);
            
            ImageIcon imagen = new ImageIcon("src/imagenes/2.jpg");
            JLabel label = new JLabel();
            label.setIcon(imagen);
            label.setBounds(160,0,(this.getWidth()-140)/2,100);
            System.out.println((this.getWidth()-140)/2);
            panel1.add(label);

            this.b.add(b);

            panel1.add(b);
            panel.add(panel1);
        }
        
        JButton aceptar =new JButton();
        aceptar.setName("Aceptar");
        aceptar.setText("Aceptar");
        aceptar.setSize(100, 30);
        
        JButton cancelar =new JButton();
        cancelar.setName("Cancelar");
        cancelar.setText("Cancelar");
        cancelar.setSize(100, 30);
        panel.setPreferredSize(new Dimension(this.getWidth(), 10+productos.size()*110));
        aceptar.setLocation((this.getWidth())-aceptar.getWidth()+5,10);
        aceptar.setFocusable(false);
        aceptar.addActionListener(this);
        b.add(aceptar);
        c.add(aceptar);
        
        cancelar.setLocation((this.getWidth())-aceptar.getWidth()+5,15+aceptar.getHeight());
        cancelar.setFocusable(false);
        cancelar.addActionListener(this);
        b.add(cancelar);
        c.add(cancelar);
        
        scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setSize(this.getWidth()-100, this.getHeight());
        this.setSize(scroll.getWidth()+aceptar.getWidth()+30,scroll.getHeight()+30);
        scroll.setLocation(0, 0);
        JScrollBar p = scroll.getVerticalScrollBar();
        p.addAdjustmentListener(this);
        c.add(scroll);
        this.repaint();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Producto p = null;
        for (int i = 0; i < b.size(); i++)
        {
            if (e.getSource()==b.get(i) && b.get(i).getName().equals("Seleccionar"))
            {
                p=productos.get(i);
                int respuesta = JOptionPane.showConfirmDialog(this, "Alguna observación?");
                String obs=null;
                if (respuesta==0)
                {
                    obs= JOptionPane.showInputDialog("Observacion:");
                }     
        
        
                PedidoProducto productoNuevo = new PedidoProducto(p, pedido, obs);
                CrudPedidoProducto controlador = new CrudPedidoProducto(productoNuevo);
                int res = controlador.insertar();
                if (res==1) 
                {
                    JOptionPane.showMessageDialog(this, "Se Agrego un producto");
                    this.nuevosProductos.add(productoNuevo);
                    contador++;
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Error agregando Producto");
                }
            }
            else
            {
                if (e.getSource()==b.get(i) && b.get(i).getName().equals("Aceptar"))
                {
                    if (contador==0) 
                    {
                        JOptionPane.showMessageDialog(this, "Debe Seleccionar al menos un producto");
                    }
                    else
                    {
                        pulsador=true;
                        this.dispose();
                    }
                }
                else
                {
                    if (e.getSource()==b.get(i) && b.get(i).getName().equals("Cancelar"))
                    {
                        this.sacarProductos();
                        pulsador=false;
                        this.dispose();
                    }
                }
            }
        }
    }

    public boolean isPulsador()
    {
        return pulsador;
    }

  @Override
    public void adjustmentValueChanged(AdjustmentEvent ae) 
    {
        for (int i = 0; i < b.size(); i++)
        {
            b.get(i).requestFocus();  
        }
    }
    
    public String descripcion(String inf)
    {
        String res=inf;
        if (inf.length()>10)
        {
            res = inf.subSequence(0, 7)+"...";
        }
        return res;
    }
    
    public Timestamp obtenerFecha()
    {
        Timestamp fechaR=null;
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
        try {
            fechaR = new java.sql.Timestamp(sdf.parse(año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo).getTime());
        } catch (ParseException ex)
        {
            //Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaR;
    }

    public ArrayList<Producto> getProductos()
    {
        return productos;
    }
    
    public void sacarProductos()
    {
        CrudPedidoProducto controlador = new CrudPedidoProducto(null);
        for (int i = 0; i < nuevosProductos.size(); i++)
        {
            PedidoProducto p = nuevosProductos.get(i);
            controlador.eliminar(p.getId(),p.getProducto().getId(),p.getPedido().getId());
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
