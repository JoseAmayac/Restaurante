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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import Crud.CrudPedidoProducto;
import proyectomesas.Pedido;
import proyectomesas.PedidoProducto;
import proyectomesas.Producto;

/**
 *
 * @author Hector
 */
public class VentanaPedido extends javax.swing.JDialog implements ActionListener,AdjustmentListener{

    Pedido pedido;
    JScrollPane scroll;
    JPanel panel;
    int pos=10;
    int pos2=10;
    ArrayList<JButton> b;
    ArrayList<PedidoProducto> productos;
    /**
     * Creates new form VentanaPedido
     * @param parent
     * @param modal
     * @param pedido
     */
    public VentanaPedido(java.awt.Frame parent, boolean modal,Pedido pedido)
    {
        super(parent, modal);
        initComponents();
        this.pedido=pedido;
        b= new ArrayList<>();
        this.pintarProductosPedidos();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    
    public void pintarProductosPedidos()
    {        

       CrudPedidoProducto productoCrud = new CrudPedidoProducto(null);
       productos = productoCrud.consultarTodosPedidos(pedido.getId());
        System.out.println(pedido.getId());
        Container c = this.getContentPane();
        panel = new JPanel();
        //panel.setBackground(Color.yellow);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setSize(this.getWidth(), this.getHeight());
        
        for (int i = 0; i < productos.size(); i++) 
        {
            Producto p = productos.get(i).getProducto();
            JPanel panel1 = new JPanel();
            //panel1.setBackground(Color.red);
            panel1.setSize(this.getWidth()-140,100);
            panel1.setLocation(10, pos2);
            pos2=110+pos2;
            panel1.setLayout(null);
            
            String nombrebtn = i+1+"";
            String descripcion = p.getDescripcion();
            String observaciones = "<br> Observaciones: ";
            if (productos.get(i).getObservaciones()!=null)
            {
                observaciones+=productos.get(i).getObservaciones();
            }
            else
            {
                observaciones = "";
            }

            String inf = "<html>Nombre: "+p.getNombre()+"<br>" + "Precio: "+p.getPrecio() +observaciones + "<br>" +"Descripcion: "+ this.descripcion(descripcion)+"<html>";

            
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
            
            ImageIcon imagen = new ImageIcon("C:/Users/Hector/Documents/NetBeansProjects/ProyectoMesas/src/imagenes/2.jpg");
            JLabel label = new JLabel();
            label.setIcon(imagen);
            label.setBounds(160,0,(this.getWidth()-140)/2,100);
            panel1.add(label);

            this.b.add(b);

            panel1.add(b);
            panel.add(panel1);
        }
        JButton agregar =new JButton();
        agregar.setName("Agregar");
        agregar.setText("Agregar Producto");
        agregar.setSize(150, 30);
       
        panel.setPreferredSize(new Dimension(this.getWidth(), 10+productos.size()*110));
        agregar.setLocation((this.getWidth())-agregar.getWidth()+55,10);
        agregar.setFocusable(false);
        agregar.addActionListener(this);
        b.add(agregar);
        c.add(agregar);
        scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setSize(this.getWidth()-100, this.getHeight());
        this.setSize(scroll.getWidth()+agregar.getWidth()+30,scroll.getHeight()+30);
        scroll.setLocation(0, 0);
        JScrollBar p = scroll.getVerticalScrollBar();
        p.addAdjustmentListener(this);
        c.add(scroll);
        this.repaint();
       // this.setComponentZOrder(panel,0);  
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
        for (int i = 0; i < b.size(); i++)
        {
            if (e.getSource()==b.get(i) && b.get(i).getText().equals("Agregar Producto"))
            {
                VentanaProductos ventana = new VentanaProductos(null, true, pedido);
                ventana.setVisible(true);
            }
            else
            {
                if (e.getSource()==b.get(i) && b.get(i).getName().equals("Seleccionar"))
                {
                    int seleccion = this.mostrarOpciones();
                    this.verificarSeleccion(seleccion, productos.get(i));
                }
            }
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public int mostrarOpciones()
    {
        int seleccion = JOptionPane.showOptionDialog( null,"Seleccione una opcion",
                     "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,null,
                    new Object[] { "Eliminar", "Modificar"},"opcion 1");
        
        return seleccion;
    }
    
    public void  removerProducto(PedidoProducto p)
    {
        CrudPedidoProducto controlador = new CrudPedidoProducto(null);
        controlador.eliminar(p.getId(), p.getProducto().getId(), p.getPedido().getId());
    }
    
    public void modificarProducto(PedidoProducto p)
    {
        String nombre = p.getProducto().getNombre();
        String obs = p.getObservaciones();
        VentanaModificar ventana = new VentanaModificar(null, true);
        ventana.setProducto(nombre);
        ventana.setObservaciones(obs);
        ventana.setVisible(true); 
        
        boolean estado = ventana.isPulsador();
        if (estado)
        {
          p.setObservaciones(ventana.getObservaciones());
          CrudPedidoProducto controlador = new CrudPedidoProducto(p);
          int control = controlador.modificar();
            if (control==1)
            {
                JOptionPane.showMessageDialog(this, "Pedido Modificado");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No se pudo modificar el pedido");
            }
        } 
    }
    
    public void verificarSeleccion(int seleccion,PedidoProducto p)
    {
        if (seleccion==0)
        {
            this.removerProducto(p);
        }
        else
        {
            if (seleccion==1)
            {
                this.modificarProducto(p);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
