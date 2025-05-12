package db_conection_package;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SolicitudDAO extends Db_Conection{
    public SolicitudDAO()
    {
        super();
    }
    
    public boolean EnviarSolicitudAmistad(int solicitud_de, int solicitud_para) 
    {
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO solicitud_amistad (remitente_solicitud_amistad, destinatario_solicitud_amistad) values (?,?)");

            ps.setInt(1, solicitud_de);
            ps.setInt(2, solicitud_para);

            boolean result = ps.executeUpdate() > 0;

            ps.close();

            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }
    
    public boolean AceptarSolicitudAmistad(int solicitud_de, int solicitud_para) 
    {
        try
        {
            PreparedStatement ps1 = getConnection().prepareStatement("INSERT INTO amistad (amigo1, amigo2) values (?,?)");

            ps1.setInt(1, solicitud_de);
            ps1.setInt(2, solicitud_para);

            PreparedStatement ps2 = getConnection().prepareStatement("DELETE FROM solicitud_amistad where (remitente_solicitud_amistad = ? AND destinatario_solicitud_amistad = ?) OR (remitente_solicitud_amistad = ? AND destinatario_solicitud_amistad = ?)");
            ps2.setInt(1, solicitud_de);
            ps2.setInt(2, solicitud_para);
            ps2.setInt(3, solicitud_para);
            ps2.setInt(4, solicitud_de);

            boolean result = ps1.executeUpdate() > 0 && ps2.executeUpdate() > 0;

            ps1.close();
            ps2.close();

            return result;
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean RechazarSolicitudAmistad(int solicitud_de, int solicitud_para) 
    {
        try
        {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM solicitud_amistad where remitente_solicitud_amistad = ? AND destinatario_solicitud_amistad = ?");
            ps.setInt(1, solicitud_de);
            ps.setInt(2, solicitud_para);

            boolean result = ps.executeUpdate() > 0;

            ps.close(); // Closing the prepared statement

            return result;
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean EliminarAmigo(int id_usuario, int id_amigo) 
    {
        try
        {
            //Eliminar los mensajes
            PreparedStatement ps1 = getConnection().prepareStatement("DELETE FROM mensaje_amigo where (remitente_amigo = ? AND destinatario_amigo = ?) OR (remitente_amigo = ? AND destinatario_amigo = ?)");
            ps1.setInt(1, id_usuario);
            ps1.setInt(2, id_amigo);
            ps1.setInt(3, id_amigo);
            ps1.setInt(4, id_usuario);
            ps1.executeUpdate();

            //Eliminar al amigo
            PreparedStatement ps2 = getConnection().prepareStatement("DELETE FROM amistad where (amigo1 = ? AND amigo2 = ?) OR (amigo1 = ? AND amigo2 = ?)");
            ps2.setInt(1, id_usuario);
            ps2.setInt(2, id_amigo);
            ps2.setInt(3, id_amigo);
            ps2.setInt(4, id_usuario);

            boolean result = ps2.executeUpdate() > 0;

            ps1.close(); // Closing the first prepared statement
            ps2.close(); // Closing the second prepared statement

            return result;
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}

