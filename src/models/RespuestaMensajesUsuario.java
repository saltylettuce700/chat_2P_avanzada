/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author panch
 */
public class RespuestaMensajesUsuario implements Serializable {
    public Date fecha_mensaje_usuario;
    public String mensaje_usuario;
    public String username_remitente;
    public String username_destinatario;
    
    public RespuestaMensajesUsuario()
    {
        this.fecha_mensaje_usuario = new Date();
        this.mensaje_usuario = "";
        this.username_remitente = "";
        this.username_destinatario = "";
    }
    
    public RespuestaMensajesUsuario(Date fecha_mensaje_usuario, String mensaje_usuario, String username_remitente, String username_destinatario)
    {
        this.fecha_mensaje_usuario = fecha_mensaje_usuario;
        this.mensaje_usuario = mensaje_usuario;
        this.username_remitente = username_remitente;
        this.username_destinatario = username_destinatario;
    }
}
