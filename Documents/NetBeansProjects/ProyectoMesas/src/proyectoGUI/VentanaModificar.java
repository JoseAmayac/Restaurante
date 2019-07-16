/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoGUI;

/**
 *
 * @author Hector
 */
public class VentanaModificar extends javax.swing.JDialog {

    /**
     * Creates new form VentanaModificar
     */
    boolean pulsador;
    
    public VentanaModificar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.pulsador=false;
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTObservaciones = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLProducto = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Producto: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 33, 80, 26));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Observaciones:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 110, 32));

        jTObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTObservacionesActionPerformed(evt);
            }
        });
        getContentPane().add(jTObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 71, 207, -1));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 33, 207, -1));
        getContentPane().add(jLProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 33, 207, -1));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 115, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 115, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoR.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTObservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTObservacionesActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        this.pulsador=true;
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.pulsador=false;
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    
    public void setProducto(String nombre)
    {
        this.jLProducto.setText(nombre);
    }
    
    public void setObservaciones(String obs)
    {
        this.jTObservaciones.setText(obs);
    }
    
    public String getObservaciones()
    {
        return this.jTObservaciones.getText();
    }

    public boolean isPulsador()
    {
        return pulsador;
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTObservaciones;
    // End of variables declaration//GEN-END:variables
}