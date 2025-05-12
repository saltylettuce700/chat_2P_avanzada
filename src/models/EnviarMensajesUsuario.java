/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author panch
 */
public class EnviarMensajesUsuario implements Serializable {
    public String remitente;
    public String destinatario;
    public String mensaje;

    public EnviarMensajesUsuario(String remitente, String destinatario, String mensaje)
    {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.mensaje = mensaje;
    }
}
