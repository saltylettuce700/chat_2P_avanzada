package db_conection;

import java.util.Date;

/**
 *
 * @author panch
 */
public class Mensaje_Amigo {
    public int id_mensaje_amigo;
    public int  remitente_amigo;
    public int  destinatario_amigo;
    public Date fecha_mensaje_amigo;
    public String mensaje_amigo;
    
    public Mensaje_Amigo()
    {
        id_mensaje_amigo = 0;
        remitente_amigo = 0;
        destinatario_amigo = 0;
        fecha_mensaje_amigo = new Date();
        mensaje_amigo = "";
    }
    
    public Mensaje_Amigo(int remitente_amigo, int destinatario_amigo, Date fecha_mensaje_amigo, String mensaje_amigo)
    {
        //autoincrementar o lo que le suponga
        this.id_mensaje_amigo = id_mensaje_amigo;
        //
        this.remitente_amigo = remitente_amigo;
        this.destinatario_amigo = destinatario_amigo;
        this.fecha_mensaje_amigo = fecha_mensaje_amigo;
        this.mensaje_amigo = mensaje_amigo;
    }
}
