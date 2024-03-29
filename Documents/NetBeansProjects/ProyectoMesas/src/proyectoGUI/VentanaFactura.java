/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGUI;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hector
 */
public class VentanaFactura extends javax.swing.JDialog 
{

    /**
     * Creates new form VentanaFactura
     * @param parent
     * @param modal
     * @param cliente
     */
    public VentanaFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(this.jLfondo.getWidth()+20, this.jLfondo.getHeight()+40);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jLCliente = new javax.swing.JLabel();
        jLFecha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLPrecio = new javax.swing.JLabel();
        jLDatos = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTProductos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLfondo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, -1, -1));

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 520, -1, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 487, 53, -1));
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 570, -1));
        getContentPane().add(jLCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 80));
        getContentPane().add(jLFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 180, 80));

        jLabel4.setText("--------------------------------------------------------------------------------------------------");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        jLabel5.setText("-------------------------------------------------------------------------------------------------");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 390, -1));

        jLabel6.setText("-------------------------------------------------------------------------------------------------");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 390, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Precio Total");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 140, 40));

        jLPrecio.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        getContentPane().add(jLPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 150, 40));
        getContentPane().add(jLDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 120, 60));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, 210));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 0, 270));

        jTProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Nombre", "Descripcion", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTProductos.setEnabled(false);
        jTProductos.setOpaque(false);
        jScrollPane2.setViewportView(jTProductos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 350, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Datos del Cliente:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 160, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Datos del Mesero:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 120, 20));

        jLfondo.setBackground(new java.awt.Color(255, 255, 255));
        jLfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 580));

        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(this, "Que tenga buen día, hasta luego.");
        
        this.dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public void setDatosCliente(String datos)
    {
        jLCliente.setText(datos);
    }
    
    
    public void setDatosFactura(String datos)
    {
        jLFecha.setText(datos);
    }
    
    public void setDatosMesero(String datos)
    {
        jLDatos.setText(datos);
    }
    
    
   public void setTabla(DefaultTableModel modelo)
   {
       jTProductos.setModel(modelo);
   }
    
    public void setPrecio(String datos)
    {
        jLPrecio.setText(datos);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLCliente;
    private javax.swing.JLabel jLDatos;
    private javax.swing.JLabel jLFecha;
    private javax.swing.JLabel jLPrecio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLfondo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTProductos;
    // End of variables declaration//GEN-END:variables
}
