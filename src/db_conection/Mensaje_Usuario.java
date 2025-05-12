package db_conection;

import java.util.Date;

/**
 *
 * @author panch
 */
public class Mensaje_Usuario {
    public int id_mensaje_usuario;
    public int  remitente_usuario;
    public int  destinatario_usuario;
    public Date fecha_mensaje_usuario;
    public String mensaje_usuario;
    public String username_remitente;
    public String username_destinatario;
    
    public Mensaje_Usuario()
    {
        id_mensaje_usuario = 0;
        remitente_usuario = 0;
        destinatario_usuario = 0;
        fecha_mensaje_usuario = new Date();
        mensaje_usuario = "";
        username_remitente = "";
        username_destinatario = "";
    }
    
    public Mensaje_Usuario(int remitente_usuario, int destinatario_usuario, Date fecha_mensaje_usuario, String mensaje_usuario, String username_destinatario, String username_remitente)
    {
        //autoincrementar o lo que le suponga
        this.id_mensaje_usuario = id_mensaje_usuario;
        //
        this.remitente_usuario = remitente_usuario;
        this.destinatario_usuario = destinatario_usuario;
        this.fecha_mensaje_usuario = fecha_mensaje_usuario;
        this.mensaje_usuario = mensaje_usuario;
        this.username_destinatario = username_destinatario;
        this.username_remitente = username_remitente;
    }
}
