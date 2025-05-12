package interfaces;

import db_conection.Usuario;
import ip_server.Direccion_IP;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.RespuestaRecuperarCuenta;
import user_session.SessionData;
import user_session.SessionManager;

/**
 *
 * @author panch
 */
public class RecuperacionCuenta extends javax.swing.JFrame {

    /**
     * Creates new form RecuperacionCuenta
     */
    public RecuperacionCuenta() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextUsuario = new javax.swing.JLabel();
        NameBoxInicioSesion = new javax.swing.JTextField();
        IngresarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TextUsuario1 = new javax.swing.JLabel();
        UsuarioRecuperacion = new javax.swing.JTextField();
        TextUsuario2 = new javax.swing.JLabel();
        AnimalRecuperacion = new javax.swing.JTextField();
        ValidarRespuesta = new javax.swing.JButton();
        VentanaRegistro = new javax.swing.JButton();

        TextUsuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextUsuario.setText("Usuario");

        NameBoxInicioSesion.setToolTipText("Ingrese su nombre de usuario");
        NameBoxInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameBoxInicioSesionActionPerformed(evt);
            }
        });

        IngresarButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        IngresarButton.setText("Ingresar");
        IngresarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarButtonActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recuperacion de cuenta");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Recuperación de cuenta");

        TextUsuario1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextUsuario1.setText("Usuario");

        UsuarioRecuperacion.setToolTipText("Ingrese su nombre de usuario");
        UsuarioRecuperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioRecuperacionActionPerformed(evt);
            }
        });

        TextUsuario2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextUsuario2.setText("¿Cual es su animal favorito? (el que ingreso al registrarse)");

        AnimalRecuperacion.setToolTipText("Ingrese el animal que ingresó al registrarse");
        AnimalRecuperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnimalRecuperacionActionPerformed(evt);
            }
        });

        ValidarRespuesta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ValidarRespuesta.setText("Validar respuesta");
        ValidarRespuesta.setToolTipText("Revisaaremos si las respuestas de su registro y la actual coinciden");
        ValidarRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValidarRespuestaActionPerformed(evt);
            }
        });

        VentanaRegistro.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VentanaRegistro.setText("Registrarse");
        VentanaRegistro.setToolTipText("Volvera a la pantalla de registro");
        VentanaRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextUsuario1)
                            .addComponent(UsuarioRecuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AnimalRecuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextUsuario2))))
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ValidarRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(331, 331, 331))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(VentanaRegistro)
                        .addGap(403, 403, 403))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(TextUsuario1)
                .addGap(18, 18, 18)
                .addComponent(UsuarioRecuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(TextUsuario2)
                .addGap(18, 18, 18)
                .addComponent(AnimalRecuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(ValidarRespuesta)
                .addGap(35, 35, 35)
                .addComponent(VentanaRegistro)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameBoxInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameBoxInicioSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameBoxInicioSesionActionPerformed

    private void UsuarioRecuperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioRecuperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioRecuperacionActionPerformed

    private void AnimalRecuperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnimalRecuperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnimalRecuperacionActionPerformed

    private void IngresarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarButtonActionPerformed


    }//GEN-LAST:event_IngresarButtonActionPerformed

    private void ValidarRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValidarRespuestaActionPerformed
        //validar las respuestas y si coinciden, mandar a la siguiente interfaz
        String usuario = UsuarioRecuperacion.getText();
        String animal = AnimalRecuperacion.getText();
        
        Usuario userRecuperacion = new Usuario(usuario,"pregunta",animal);
        Socket s;
        try {
            String direccionServidor = Direccion_IP.IP_SERVER;
            InetAddress direccion = InetAddress.getByName(direccionServidor);
            s = new Socket(direccion, 1234);
            
            DataOutputStream funcion = new DataOutputStream(s.getOutputStream());
            funcion.writeUTF("recuperar_cuenta_validar");
            
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(s.getOutputStream());
            salidaObjeto.writeObject(userRecuperacion);
            
            ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
            RespuestaRecuperarCuenta respuesta;
            
            String username = "";
            String Redirigir = "";
            try {
                respuesta = (RespuestaRecuperarCuenta) inputStream.readObject();
                Redirigir = respuesta.resultadoFuncion;
                username = respuesta.username;
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RecuperacionCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            SessionManager.createSession(username);
            SessionData sessionData = SessionManager.getSession();
            
            if(sessionData != null)
            {
                System.out.println("Id de usuario: " + sessionData.getUsername());
            }
            
            System.out.println(Redirigir);
            s.close();
            
            if(Redirigir.equals("redirigir_recuperar_contrasena"))
            {
                setVisible(false);
                NuevaContrasena a = new NuevaContrasena();
                a.setVisible(true);
                this.setVisible(false);
            }
            else if(Redirigir.equals("error_fallo_pregunta_o_usuario"))
            {
                System.out.println("Error, informacion incorrecta");
                JOptionPane.showMessageDialog(null, "Error.Informacion incorrecta", "Error", HEIGHT);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ValidarRespuestaActionPerformed

    private void VentanaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentanaRegistroActionPerformed
        Registro a = new Registro();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_VentanaRegistroActionPerformed

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
            java.util.logging.Logger.getLogger(RecuperacionCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecuperacionCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecuperacionCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecuperacionCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperacionCuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AnimalRecuperacion;
    private javax.swing.JButton IngresarButton;
    private javax.swing.JTextField NameBoxInicioSesion;
    private javax.swing.JLabel TextUsuario;
    private javax.swing.JLabel TextUsuario1;
    private javax.swing.JLabel TextUsuario2;
    private javax.swing.JTextField UsuarioRecuperacion;
    private javax.swing.JButton ValidarRespuesta;
    private javax.swing.JButton VentanaRegistro;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
