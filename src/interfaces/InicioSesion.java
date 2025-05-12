package interfaces;

import db_conection.Usuario;
import ip_server.Direccion_IP;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import user_session.SessionManager;
/**
 * @author panch
 */
public class InicioSesion extends javax.swing.JFrame {

    public InicioSesion() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jFrame3 = new javax.swing.JFrame();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jDialog1 = new javax.swing.JDialog();
        ConstrasenaInicioSesion = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        TextUsuario = new javax.swing.JLabel();
        NameBoxInicioSesion = new javax.swing.JTextField();
        passText = new javax.swing.JLabel();
        IngresarButton = new javax.swing.JButton();
        VentanaRegistrarseButton = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de sesion");
        setExtendedState(6);
        setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        setResizable(false);

        ConstrasenaInicioSesion.setToolTipText("Ingrese la contraseña de su cuenta");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Inicio de sesión");

        TextUsuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        TextUsuario.setText("Usuario");

        NameBoxInicioSesion.setToolTipText("Ingrese su nombre de usuario");
        NameBoxInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameBoxInicioSesionActionPerformed(evt);
            }
        });

        passText.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        passText.setText("Contraseña");

        IngresarButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        IngresarButton.setText("Ingresar");
        IngresarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarButtonActionPerformed(evt);
            }
        });

        VentanaRegistrarseButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VentanaRegistrarseButton.setText("Registrarse");
        VentanaRegistrarseButton.setToolTipText("En caso de no tener una cuenta, lo enviara a crearse una");
        VentanaRegistrarseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentanaRegistrarseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passText)
                    .addComponent(TextUsuario)
                    .addComponent(NameBoxInicioSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(ConstrasenaInicioSesion))
                .addContainerGap(591, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(319, 319, 319))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VentanaRegistrarseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IngresarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(348, 348, 348))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(TextUsuario)
                .addGap(18, 18, 18)
                .addComponent(NameBoxInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(passText)
                .addGap(18, 18, 18)
                .addComponent(ConstrasenaInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(IngresarButton)
                .addGap(18, 18, 18)
                .addComponent(VentanaRegistrarseButton)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameBoxInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameBoxInicioSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameBoxInicioSesionActionPerformed

    private void IngresarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarButtonActionPerformed
        //obtener los datos del formulario del inicio de sesion
        String usuario = NameBoxInicioSesion.getText();
        
        char[] obtenerContrasena = ConstrasenaInicioSesion.getPassword();
        String contrasena = new String(obtenerContrasena);
        
        Usuario userLogin = new Usuario(usuario, "password",contrasena);
        Socket s;
        try {
            String direccionServidor = Direccion_IP.IP_SERVER;
            InetAddress direccion = InetAddress.getByName(direccionServidor);
            s = new Socket(direccion, 1234);
            
            DataOutputStream funcion = new DataOutputStream(s.getOutputStream());
            funcion.writeUTF("login");
            
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(s.getOutputStream());
            salidaObjeto.writeObject(userLogin);
            
            DataInputStream salidaRedirigir = new DataInputStream(s.getInputStream());
            String Redirigir = salidaRedirigir.readUTF();
            
            System.out.println(Redirigir);
            s.close();
            
            if(Redirigir.equals("credenciales_validas"))
            {
                SessionManager.createSession(userLogin.username);
                Usuarios a = new Usuarios();
                a.setVisible(true);
                this.setVisible(false);
            }
            if(Redirigir.equals("redirigir"))
            {
                Registro a = new Registro();
                a.setVisible(true);
                this.setVisible(false);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Usuario: " + usuario);
        System.out.println("Contrasena: " + contrasena);
        
    }//GEN-LAST:event_IngresarButtonActionPerformed

    private void VentanaRegistrarseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentanaRegistrarseButtonActionPerformed
        RegistroSinRecuperarCuenta a = new RegistroSinRecuperarCuenta();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_VentanaRegistrarseButtonActionPerformed

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
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ConstrasenaInicioSesion;
    private javax.swing.JButton IngresarButton;
    private javax.swing.JTextField NameBoxInicioSesion;
    private javax.swing.JLabel TextUsuario;
    private javax.swing.JButton VentanaRegistrarseButton;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel passText;
    // End of variables declaration//GEN-END:variables
}