package db_conection;

/**
 *
 * @author panch
 */
public class Solicitud_Amistad {
    public int id_solicitud_amistad;
    public int remitente_solicitud_amistad;
    public int destinatario_solicitud_amistad;
    
    public Solicitud_Amistad()
    {
        id_solicitud_amistad = 0;
        remitente_solicitud_amistad = 0;
        destinatario_solicitud_amistad = 0;
    }
    
    public Solicitud_Amistad(int remitente_solicitud_amistad, int destinatario_solicitud_amistad)
    {
        this.id_solicitud_amistad = id_solicitud_amistad;
        this.remitente_solicitud_amistad = remitente_solicitud_amistad;
        this.destinatario_solicitud_amistad = destinatario_solicitud_amistad;
    }
}
