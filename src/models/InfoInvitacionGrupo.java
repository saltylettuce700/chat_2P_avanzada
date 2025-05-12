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
public class InfoInvitacionGrupo  implements Serializable {
    public String grupo_invitado;
    public String remitente_invitacion_grupo;
    public String destinatario_invitacion_grupo;

    
    public InfoInvitacionGrupo()
    {
        grupo_invitado = "";
        remitente_invitacion_grupo = "";
        destinatario_invitacion_grupo = "";
    }
    
    public InfoInvitacionGrupo(String grupo_invitado, String remitente_invitacion_grupo, String destinatario_invitacion_grupo)
    {
        this.grupo_invitado = grupo_invitado;
        this.remitente_invitacion_grupo = remitente_invitacion_grupo;
        this.destinatario_invitacion_grupo = destinatario_invitacion_grupo;
    }
}
