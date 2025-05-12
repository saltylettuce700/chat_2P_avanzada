package returned_models;

import java.io.Serializable;

public class InfoSolicitudAmistad implements Serializable {
    public String remitente_solicitud_amistad;
    public String destinatario_solicitud_amistad;
    
    public InfoSolicitudAmistad()
    {
        remitente_solicitud_amistad = "";
        destinatario_solicitud_amistad = "";
    }
    
    public InfoSolicitudAmistad(String remitente_solicitud_amistad, String destinatario_solicitud_amistad)
    {
        this.remitente_solicitud_amistad = remitente_solicitud_amistad;
        this.destinatario_solicitud_amistad = destinatario_solicitud_amistad;
    }
}
