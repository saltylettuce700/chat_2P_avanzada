package db_conection_package;
import java.util.Date;

public class Mensaje_Grupo {
    public int id_mensaje_grupo;
    public int  remitente_grupo;
    public int  destinatario_grupo;
    public Date fecha_mensaje_grupo;
    public String mensaje_grupo;
    
    public Mensaje_Grupo()
    {
        id_mensaje_grupo = 0;
        remitente_grupo = 0;
        destinatario_grupo = 0;
        fecha_mensaje_grupo = new Date();
        mensaje_grupo = "";
    }
    
    public Mensaje_Grupo(int remitente_grupo, int destinatario_grupo, Date fecha_mensaje_grupo, String mensaje_grupo)
    {
        //autoincrementar o lo que le suponga
        this.id_mensaje_grupo = id_mensaje_grupo;
        //
        this.remitente_grupo = remitente_grupo;
        this.destinatario_grupo = destinatario_grupo;
        this.fecha_mensaje_grupo = fecha_mensaje_grupo;
        this.mensaje_grupo = mensaje_grupo;
    }
}
