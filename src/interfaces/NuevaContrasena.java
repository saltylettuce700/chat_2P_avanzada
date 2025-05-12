/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import db_conection.Usuario;
import ip_server.Direccion_IP;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import user_session.SessionData;
import user_session.SessionManager;

/**
 *
 * @author panch
 */
public class NuevaContrasena extends javax.swing.JFrame {

    /**
     * Creates new form NuevaContrasena
     */
    public NuevaContrasena() {
        initComponents();
        this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        passNuevaContrasena = new javax.swing.JPasswordField();
        VentanaInicioSesion = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Registro");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nueva contraseña");
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Nueva contraseña");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Nueva contraseña");
        jLabel3.setToolTipText("");

        passNuevaContrasena.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        passNuevaContrasena.setToolTipText("Ingrese la nueva contraseña para su cuenta");

        VentanaInicioSesion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VentanaInicioSesion.setText("Iniciar sesión");
        VentanaInicioSesion.setToolTipText("");
        VentanaInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaInicioSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passNuevaContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(261, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(246, 246, 246))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(VentanaInicioSesion)
                        .addGap(300, 300, 300))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel2)
                .addGap(68, 68, 68)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(passNuevaContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(VentanaInicioSesion)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VentanaInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentanaInicioSesionActionPerformed
        
        char[] obtenerContrasena = passNuevaContrasena.getPassword();
        String contrasena = new String(obtenerContrasena);
        
        SessionData sessionData = SessionManager.getSession();
        Usuario userNewPass = new Usuario(sessionData.getUsername(), "password", contrasena);
        Socket s;
        
        try {
            String direccionServidor = Direccion_IP.IP_SERVER;
            InetAddress direccion = InetAddress.getByName(direccionServidor);
            s = new Socket(direccion, 1234);
            
            DataOutputStream funcion = new DataOutputStream(s.getOutputStream());
            funcion.writeUTF("recuperar_contrasena");
            
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(s.getOutputStream());
            salidaObjeto.writeObject(userNewPass);
            
            DataInputStream salidaRedirigir = new DataInputStream(s.getInputStream());
            
            String Redirigir = salidaRedirigir.readUTF();
            
            JOptionPane.showMessageDialog(null, "Error.Informacion incorrecta", "Error", HEIGHT);
            
            System.out.println(Redirigir);
            s.close();
            
            if(Redirigir.equals("redirigir"))
            {
                Registro a = new Registro();
                a.setVisible(true);
                this.setVisible(false);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nuevaPass = new String(obtenerContrasena);
        System.out.println("Nueva contrasena: " + nuevaPass);
        
        InicioSesion a = new InicioSesion();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_VentanaInicioSesionActionPerformed

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
            java.util.logging.Logger.getLogger(NuevaContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevaContrasena().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VentanaInicioSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField passNuevaContrasena;
    // End of variables declaration//GEN-END:variables
}
