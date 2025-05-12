/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import ip_server.Direccion_IP;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.EnviarMensajesUsuario;
import models.RespuestaMensajesAmigo;
import models.RespuestaMensajesGrupo;
import models.RespuestaMensajesUsuario;
import models.SolicitarMensajesUsuario;
import user_session.SessionManager;


/**
 *
 * @author panch
 */
public class Chat extends javax.swing.JFrame {
    private String destinatario;
    private String username;
    private String ventana;
         
    /**
     * Creates new form Chat
     */
    public Chat()
    {
    
    }
    public Chat(String username, String ventana) {
        this.destinatario = username;
        this.ventana = ventana;
        initComponents();
        
        Socket s;
        try {
            String direccionServidor = Direccion_IP.IP_SERVER;
            InetAddress direccion = InetAddress.getByName(direccionServidor);
            s = new Socket(direccion, 1234);
            
            String remitente = SessionManager.getUsername();
            Color colorDestinatario = Color.BLUE;
            Color colorRemitente = Color.GREEN;
            SolicitarMensajesUsuario solicitudMensaje = new SolicitarMensajesUsuario(remitente, this.destinatario);
            ObjectOutputStream solicitud;
            ObjectInputStream chat;
            
            DataOutputStream funcion = new DataOutputStream(s.getOutputStream());
            switch (ventana) {
                case "Usuarios":
                    funcion.writeUTF("cargar_mensajes_usuario");
                    solicitud = new ObjectOutputStream(s.getOutputStream());
                    solicitud.writeObject(solicitudMensaje);
                    
                    chat = new ObjectInputStream(s.getInputStream());

                    try {
                        ArrayList<RespuestaMensajesUsuario> chatRecibido = (ArrayList<RespuestaMensajesUsuario>)chat.readObject();
                        s.close();

                        for(RespuestaMensajesUsuario msg: chatRecibido)
                        {

                            System.out.println(msg.username_destinatario);
                            System.out.println(msg.username_remitente);

                            if(msg.username_destinatario.equals(remitente))
                            {
                                Color color = remitente.equals(msg.username_remitente) ? colorRemitente : colorDestinatario;
                                CampoChat.append(msg.username_remitente + ": " + msg.mensaje_usuario + "\n");
                                CampoChat.setCaretPosition(CampoChat.getDocument().getLength());
                            }
                            else
                            {
                                Color color = remitente.equals(msg.username_remitente) ? colorRemitente : colorDestinatario;
                                CampoChat.append(msg.username_remitente + ": " + msg.mensaje_usuario + "\n");
                                CampoChat.setCaretPosition(CampoChat.getDocument().getLength());
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "Amigos":
                    funcion.writeUTF("cargar_mensajes_amigo");
                    solicitud = new ObjectOutputStream(s.getOutputStream());
                    solicitud.writeObject(solicitudMensaje);
                    
                    chat = new ObjectInputStream(s.getInputStream());

                    try {
                        ArrayList<RespuestaMensajesAmigo> chatRecibido = (ArrayList<RespuestaMensajesAmigo>)chat.readObject();
                        s.close();

                        for(RespuestaMensajesAmigo msg: chatRecibido)
                        {

                            System.out.println(msg.username_destinatario);
                            System.out.println(msg.username_remitente);

                            if(msg.username_destinatario.equals(remitente))
                            {
                                Color color = remitente.equals(msg.username_remitente) ? colorRemitente : colorDestinatario;
                                CampoChat.append(msg.username_remitente + ": " + msg.mensaje_amigo + "\n");
                                CampoChat.setCaretPosition(CampoChat.getDocument().getLength());
                            }
                            else
                            {
                                Color color = remitente.equals(msg.username_remitente) ? colorRemitente : colorDestinatario;
                                CampoChat.append(msg.username_remitente + ": " + msg.mensaje_amigo + "\n");
                                CampoChat.setCaretPosition(CampoChat.getDocument().getLength());
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "Grupos":
                    funcion.writeUTF("cargar_mensajes_grupo");
                    solicitud = new ObjectOutputStream(s.getOutputStream());
                    solicitud.writeObject(solicitudMensaje);
                    
                    chat = new ObjectInputStream(s.getInputStream());

                    try {
                        ArrayList<RespuestaMensajesGrupo> chatRecibido = (ArrayList<RespuestaMensajesGrupo>)chat.readObject();
                        s.close();

                        for(RespuestaMensajesGrupo msg: chatRecibido)
                        {
                            System.out.println(msg.username_remitente);

                                Color color = remitente.equals(msg.username_remitente) ? colorRemitente : colorDestinatario;
                                CampoChat.append(msg.username_remitente + ": " + msg.mensaje_grupo + "\n");
                                CampoChat.setCaretPosition(CampoChat.getDocument().getLength());
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    break;
            }
            

            
            
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        EnviarMsgButton = new javax.swing.JButton();
        campoMsg = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        CampoChat = new javax.swing.JTextArea();
        RecargarButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(226, 380));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        EnviarMsgButton.setText("Enviar");
        EnviarMsgButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnviarMsgButtonMouseClicked(evt);
            }
        });
        EnviarMsgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarMsgButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(EnviarMsgButton, gridBagConstraints);

        campoMsg.setColumns(15);
        campoMsg.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        campoMsg.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(campoMsg, gridBagConstraints);

        CampoChat.setEditable(false);
        CampoChat.setColumns(15);
        CampoChat.setRows(15);
        jScrollPane1.setViewportView(CampoChat);

        getContentPane().add(jScrollPane1, new java.awt.GridBagConstraints());

        RecargarButton.setText("Recargar");
        RecargarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecargarButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        getContentPane().add(RecargarButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnviarMsgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarMsgButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnviarMsgButtonActionPerformed

    private void EnviarMsgButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnviarMsgButtonMouseClicked
        String mensaje = campoMsg.getText();
        String remitente = SessionManager.getUsername();
        
        EnviarMensajesUsuario msg = new EnviarMensajesUsuario(remitente, this.destinatario, mensaje);
        ObjectOutputStream msgEnviar;
        ObjectInputStream chat;
        
               
        Socket s;
        try {
            String direccionServidor = Direccion_IP.IP_SERVER;
            InetAddress direccion = InetAddress.getByName(direccionServidor);
            s = new Socket(direccion, 1234);
            
            DataOutputStream funcion = new DataOutputStream(s.getOutputStream());
            switch (ventana) {
                case "Usuarios":
                    funcion.writeUTF("enviar_mensaje_usuario");
                    msgEnviar = new ObjectOutputStream(s.getOutputStream());
                    msgEnviar.writeObject(msg);
                    try {
                        chat = new ObjectInputStream(s.getInputStream());

                        ArrayList<RespuestaMensajesUsuario> chatRecibido = (ArrayList<RespuestaMensajesUsuario>)chat.readObject();
                        s.close();

                        Color colorDestinatario = Color.BLUE;
                        Color colorRemitente = Color.GREEN;
                        for(RespuestaMensajesUsuario msge: chatRecibido)
                        {

                            System.out.println(msge.username_destinatario);
                            System.out.println(msge.username_remitente);

                            
                            Color color = remitente.equals(msge.username_remitente) ? colorRemitente : colorDestinatario;
                            CampoChat.append(msge.username_remitente + ": " + msge.mensaje_usuario + "\n");
                            CampoChat.setCaretPosition(CampoChat.getDocument().getLength());
                            
                        }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    break;
                case "Amigos":
                    funcion.writeUTF("enviar_mensaje_amigo");
                    msgEnviar = new ObjectOutputStream(s.getOutputStream());
                    msgEnviar.writeObject(msg); 
                    
                    try {
                        chat = new ObjectInputStream(s.getInputStream());
                        ArrayList<RespuestaMensajesAmigo> chatRecibido = (ArrayList<RespuestaMensajesAmigo>)chat.readObject();
                        s.close();

                        Color colorDestinatario = Color.BLUE;
                        Color colorRemitente = Color.GREEN;
                        for(RespuestaMensajesAmigo msge: chatRecibido)
                        {

                            System.out.println(msge.username_destinatario);
                            System.out.println(msge.username_remitente);

                            Color color = remitente.equals(msge.username_remitente) ? colorRemitente : colorDestinatario;
                            CampoChat.append(msge.username_remitente + ": " + msge.mensaje_amigo + "\n");
                            CampoChat.setCaretPosition(CampoChat.getDocument().getLength());
                            
                        }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    break;
                case "Grupos":
                    funcion.writeUTF("enviar_mensaje_grupo");
                    msgEnviar = new ObjectOutputStream(s.getOutputStream());
                    msgEnviar.writeObject(msg);
                       
                    try {
                        chat = new ObjectInputStream(s.getInputStream());

                        ArrayList<RespuestaMensajesGrupo> chatRecibido = (ArrayList<RespuestaMensajesGrupo>)chat.readObject();
                        s.close();

                        Color colorDestinatario = Color.BLUE;
                        Color colorRemitente = Color.GREEN;
                        for(RespuestaMensajesGrupo msge: chatRecibido)
                        {

                            System.out.println(msge.username_remitente);

                            Color color = remitente.equals(msge.username_remitente) ? colorRemitente : colorDestinatario;
                            CampoChat.append(msge.username_remitente + ": " + msge.mensaje_grupo + "\n");
                            CampoChat.setCaretPosition(CampoChat.getDocument().getLength());
                        }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    break;
                default:
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EnviarMsgButtonMouseClicked

    private void RecargarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecargarButtonActionPerformed
        Chat a = new Chat();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RecargarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea CampoChat;
    private javax.swing.JButton EnviarMsgButton;
    private javax.swing.JToggleButton RecargarButton;
    private javax.swing.JTextField campoMsg;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
