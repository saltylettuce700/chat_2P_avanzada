package returned_models;

import java.io.Serializable;

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