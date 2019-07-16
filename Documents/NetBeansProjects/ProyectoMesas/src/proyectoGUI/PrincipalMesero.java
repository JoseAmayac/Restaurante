/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGUI;

import java.awt.Button;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import Crud.CrudMesa;
import Crud.CrudPedido;
import javax.swing.ImageIcon;
import proyectomesas.Mesa;
import proyectomesas.Mesero;
import proyectomesas.Pedido;

/**
 *
 * @author Hector
 */
public class PrincipalMesero extends javax.swing.JDialog implements ActionListener,AdjustmentListener{

    Mesero mesero;
    int pos = 10;
    int pos2 = 10;
    ArrayList<Button> b;
    ArrayList<Button> factura;
    JScrollPane scroll;
    JPanel panel;
    ArrayList<Mesa> mesas;
    ImageIcon imagen;
    
    /**
     * Creates new form PrincipalMesero
     */
    public PrincipalMesero(java.awt.Frame parent, boolean modal,Mesero mesero)
    {
        super(parent, modal);
        initComponents();
        this.mesero=mesero;
       // this.jlMensaje.setText(mesero.getNombres()+" "+mesero.getApellidos());
        b=new ArrayList<>();
        this.setTitle(mesero.getNombres() + " " + mesero.getApellidos());
        
        
        factura=new ArrayList<>();
        this.pintarMesas();
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void pintarMesas()
    {        
       Mesa mesa = new Mesa("", 0,"");
       CrudMesa mesaCrud = new CrudMesa(mesa);
       mesas = mesaCrud.consultarTodos();

        Container c = this.getContentPane();
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        panel.setSize(this.getWidth(), this.getHeight());
        
        for (int i = 0; i < mesas.size(); i++) 
        {
            JPanel panel1 = new JPanel();
            panel1.setSize(this.getWidth()-140,100);
            panel1.setLocation(10, pos2);
            panel1.setLayout(null);
            pos2=110+pos2;
            JLabel l = new JLabel(mesas.get(i).getNumero()+" "+mesas.get(i).getEstado());
            l.setLocation(0,0);
            l.setSize(100,50);
            l.setHorizontalAlignment(SwingConstants.CENTER);
           
            panel1.add(l);
            
            if (!mesas.get(i).getEstado().equals("ocupada"))
            {
                String nombrebtn = i+1+"";
                Button b =new Button();
                b.setName(nombrebtn);
                b.setLabel("Realizar Pedido");
                b.setLocation(150, 10);
                b.setSize(110,25);
                b.addActionListener(this);
                b.setBackground(Color.cyan);
                this.b.add(b);
                panel1.add(b);
            }
            else
            {
                String nombrebtn = i+"a";
                Button b =new Button();
                b.setName(nombrebtn);
                b.setLabel("Ver Pedido");
                b.setLocation(150, 10);
                b.setSize(100,25);
                b.addActionListener(this);
                b.setBackground(Color.green);
                this.b.add(b);
                panel1.add(b);
                
                Button b1 =new Button();
                b1.setName("Generar");
                b1.setLabel("Generar Factura");
                b1.setLocation(150, 20 + b.getHeight());
                b1.setSize(100,25);
                b1.addActionListener(this);
                b1.setBackground(Color.green);
                this.factura.add(b1);
                panel1.add(b1);
            }
            panel1.repaint();
            panel1.setBackground(new Color(236, 188, 45));
            panel.add(panel1);
        }
        panel.setPreferredSize(new Dimension(this.getWidth(), 10+mesas.size()*110));
        scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setSize(this.getWidth()-100, this.getHeight());
        this.setSize(scroll.getWidth()+5,scroll.getHeight()+30);
        scroll.setLocation(0, 0);
        JScrollBar p = scroll.getVerticalScrollBar();
        p.addAdjustmentListener(this);
        c.add(scroll);
        
        this.repaint();
       // this.setComponentZOrder(panel,0);  
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(395, 395, 395)
                .addComponent(jlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(368, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Mesa m = null;
        for (int i = 0; i < b.size(); i++)
        {
            if (e.getSource()==b.get(i)&&b.get(i).getLabel().equals("Realizar Pedido"))
            {
                m=mesas.get(i);
                System.out.println(m.getNumero()+"PEDIO");
                Pedido pedido = new Pedido(m, this.obtenerFecha(), mesero);
                CrudPedido crudpedido = new CrudPedido(pedido);
                int res = crudpedido.insertar();
                m.setEstado("ocupada");
                CrudMesa crudMesa  = new CrudMesa(m);
                crudMesa.modificar();
                VentanaProductos ventana = new VentanaProductos(null, true,pedido);
                ventana.setVisible(true);
                boolean estado = ventana.isPulsador();
                if (!estado)
                {
                    m.setEstado("libre");
                    CrudMesa crudMesa2  = new CrudMesa(m);
                    crudMesa2.modificar();
                    crudpedido.eliminar(pedido.getId());
                }
            }
            else
            {
                if (e.getSource()==b.get(i)&&b.get(i).getLabel().equals("Ver Pedido"))
                {
                    m=mesas.get(i);
                    System.out.println(m.getNumero()+"VERPE");
                    CrudPedido crudpedido = new CrudPedido(null);
                    int id = crudpedido.consultarMaximo(m.getNumero());
                    Pedido pedido = crudpedido.consultar(id);
                    VentanaPedido ventana2 = new VentanaPedido(null, true,pedido);
                    ventana2.setVisible(true);
                }      
            }
        }
        
        for (int i = 0; i < factura.size(); i++)
        {
            if(e.getSource()==factura.get(i)&&factura.get(i).getName().equals("Generar"))
            {
                m=mesas.get(i);
                System.out.println(m.getNumero());
                CrudPedido crudpedido = new CrudPedido(null);
                int id = crudpedido.consultarMaximo(m.getNumero());
                Pedido pedido = crudpedido.consultar(id);
                VentanaBuscar factura = new VentanaBuscar(null,true,pedido);
                factura.setVisible(true);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlMensaje;
    // End of variables declaration//GEN-END:variables

    @Override
    public void adjustmentValueChanged(AdjustmentEvent ae) 
    {
        for (int i = 0; i < b.size(); i++)
        {
            b.get(i).requestFocus() ;  
        }
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
}
