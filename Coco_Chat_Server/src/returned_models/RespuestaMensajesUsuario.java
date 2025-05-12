package returned_models;

import java.io.Serializable;
import java.util.Date;

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
