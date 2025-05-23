package returned_models;

import java.io.Serializable;
import java.util.Date;

public class RespuestaMensajesAmigo implements Serializable {
    public Date fecha_mensaje_amigo;
    public String mensaje_amigo;
    public String username_remitente;
    public String username_destinatario;
    
    public RespuestaMensajesAmigo()
    {
        this.fecha_mensaje_amigo = new Date();
        this.mensaje_amigo = "";
        this.username_remitente = "";
        this.username_destinatario = "";
    }
    
    public RespuestaMensajesAmigo(Date fecha_mensaje_amigo, String mensaje_amigo, String username_remitente, String username_destinatario)
    {
        this.fecha_mensaje_amigo = fecha_mensaje_amigo;
        this.mensaje_amigo = mensaje_amigo;
        this.username_remitente = username_remitente;
        this.username_destinatario = username_destinatario;
    }
}
