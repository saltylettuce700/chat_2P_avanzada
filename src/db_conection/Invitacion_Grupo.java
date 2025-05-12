package db_conection;

/**
 *
 * @author panch
 */
public class Invitacion_Grupo {
    public int id_invitacion_grupo;
    public int id_grupo_invitado;
    public int remitente_invitacion_grupo;
    public int destinatario_invitacion_grupo;
    
    public Invitacion_Grupo()
    {
        id_invitacion_grupo = 0;
        id_grupo_invitado = 0;
        remitente_invitacion_grupo = 0;
        destinatario_invitacion_grupo = 0;
    }
    
    public Invitacion_Grupo(int id_grupo_invitado, int remitente_invitacion_grupo, int destinatario_invitacion_grupo)
    {
        this.id_invitacion_grupo = id_invitacion_grupo;
        this.id_grupo_invitado = id_grupo_invitado;
        this.remitente_invitacion_grupo = remitente_invitacion_grupo;
        this.destinatario_invitacion_grupo = destinatario_invitacion_grupo;
    }
}
