/*
 * Copyright (C) 2015 Alfredo Rouse Madrigal
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package vista;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import remoteAccess.InicioServidor;
import vista.controlador.Validador;

/**
 * JFrame que mostrara la interfaz gráfica del servidor.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 27 Mayo 2015
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * @see remoteAccess.InicioServidor
     */
    private InicioServidor servidor;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para iniciar el servidor.
    */
    private javax.swing.JButton btnIniciar;
    /**
    * Label para mostrar cuando el servidor fue iniciado.
    */
    private javax.swing.JLabel lblInicioServidor;
    /**
    * Label para indicar el puerto.
    */
    private javax.swing.JLabel lblPuerto;
    /**
    * Label para el título de la interfaz.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Campo de texto para ingresar el puerto.
    */
    private javax.swing.JTextField txtfPuerto;
    // End of variables declaration//GEN-END:variables

    /**
     * Craa un nuevo FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        servidor = new InicioServidor();
        lblInicioServidor.setVisible(false);
        agregarIconos();
    }

    /**
     * Agrega los iconos a la interfaz gráfica y a la barra de tareas.
     */
    private void agregarIconos() {
        List<Image> icons = new ArrayList<>();
        icons.add(new ImageIcon(getClass().getResource("/recursos/icono16.png")).getImage());
        icons.add(new ImageIcon(getClass().getResource("/recursos/icono32.png")).getImage());
        this.setIconImages(icons);
    }

    /**
     * Crea e inicializa los componentes gráficos de la interfaz.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPuerto = new javax.swing.JLabel();
        txtfPuerto = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        lblInicioServidor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor");
        setResizable(false);

        lblPuerto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPuerto.setText("Puerto:");

        txtfPuerto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfPuerto.setText("9000");
        txtfPuerto.setPreferredSize(new java.awt.Dimension(100, 30));
        txtfPuerto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfPuertoKeyTyped(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Servidor");

        btnIniciar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar24.png"))); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.setPreferredSize(new java.awt.Dimension(65, 30));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        lblInicioServidor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblInicioServidor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInicioServidor.setText("Servidor Iniciado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(lblPuerto)
                .addGap(44, 44, 44)
                .addComponent(txtfPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInicioServidor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTitulo)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPuerto)
                    .addComponent(txtfPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(lblInicioServidor)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Inicia el servidor en el purto especificado.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        int puerto;
        try {
            puerto = Integer.parseInt(txtfPuerto.getText());
            servidor.iniciarServidor(puerto);
            btnIniciar.setEnabled(false);
            lblInicioServidor.setVisible(true);
            txtfPuerto.setEnabled(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresar solo números",
                    "Inicio servidor", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtfPuertoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfPuertoKeyTyped
        char tecla = evt.getKeyChar();
        if(!Validador.esNumero(String.valueOf(tecla))){
            evt.consume();
        }
    }//GEN-LAST:event_txtfPuertoKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }


}
