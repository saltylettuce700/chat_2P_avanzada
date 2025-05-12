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
public class RespuestaMensajesGrupo implements Serializable {
    public Date fecha_mensaje_grupo;
    public String mensaje_grupo;
    public String username_remitente;
    
    public RespuestaMensajesGrupo()
    {
        this.fecha_mensaje_grupo = new Date();
        this.mensaje_grupo = "";
        this.username_remitente = "";
    }
    
    public RespuestaMensajesGrupo(Date fecha_mensaje_grupo, String mensaje_grupo, String username_remitente)
    {
        this.fecha_mensaje_grupo = fecha_mensaje_grupo;
        this.mensaje_grupo = mensaje_grupo;
        this.username_remitente = username_remitente;
    }
}
