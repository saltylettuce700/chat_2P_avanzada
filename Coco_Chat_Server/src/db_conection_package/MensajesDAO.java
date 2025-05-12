package db_conection_package;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import returned_models.*;

public class MensajesDAO extends Db_Conection{
    public MensajesDAO()
    {
        super();
    }
    
    public boolean EnviarMensajeUsuario(int mensaje_de, int mensaje_para, String mensaje) 
    {
        try
        {
            
        Timestamp fecha_actual = new Timestamp(System.currentTimeMillis());

        PreparedStatement ps = getConnection().prepareStatement("INSERT INTO mensaje_usuario (remitente_usuario, destinatario_usuario, fecha_mensaje_usuario, mensaje_usuario) values (?,?,?,?)");
        
        ps.setInt(1, mensaje_de);
        ps.setInt(2, mensaje_para);
        ps.setTimestamp(3, fecha_actual);
        ps.setString(4, mensaje);
        
        return ps.executeUpdate()>0;
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean EliminarMensajeUsuario(int usuario) 
    {
        try
        {
        PreparedStatement ps = getConnection().prepareStatement("DELETE FROM mensaje_usuario where (remitente_usuario = ? OR destinatario_usuario = ?)");
        
        ps.setInt(1, usuario);
        ps.setInt(2, usuario);
        
        return ps.executeUpdate()>0;
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean EnviarMensajeAmigo(int mensaje_de, int mensaje_para, String mensaje) 
    {
        try
        {
            Timestamp fecha_actual = new Timestamp(System.currentTimeMillis());

            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO mensaje_amigo (remitente_amigo, destinatario_amigo, fecha_mensaje_amigo, mensaje_amigo) values (?,?,?,?)");

            ps.setInt(1, mensaje_de);
            ps.setInt(2, mensaje_para);
            ps.setTimestamp(3, fecha_actual);
            ps.setString(4, mensaje);

            return ps.executeUpdate()>0;
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }


    public boolean EnviarMensajeGrupo(int mensaje_de, int mensaje_para, String mensaje) 
    {
        try
        {
            
        Timestamp fecha_actual = new Timestamp(System.currentTimeMillis());

        PreparedStatement ps = getConnection().prepareStatement("INSERT INTO mensaje_grupo (remitente_grupo, destinatario_grupo, fecha_mensaje_grupo, mensaje_grupo) values (?,?,?,?)");
        
        ps.setInt(1, mensaje_de);
        ps.setInt(2, mensaje_para);
        ps.setTimestamp(3, fecha_actual);
        ps.setString(4, mensaje);
        
        return ps.executeUpdate()>0;
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public ArrayList<RespuestaMensajesUsuario> obtenerMensajesUsuario(int usuario, int usuario_mensaje) {
    ArrayList<RespuestaMensajesUsuario> listaMensajesUsuario = new ArrayList<>();
    try (PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM mensaje_usuario where (remitente_usuario = ? AND destinatario_usuario = ?) OR (remitente_usuario = ? AND destinatario_usuario = ?)")) {
        ps.setInt(1, usuario);
        ps.setInt(2, usuario_mensaje);
        ps.setInt(3, usuario_mensaje);
        ps.setInt(4, usuario);

        try (ResultSet rs = ps.executeQuery()) {
            RespuestaMensajesUsuario mensajeUsuario;
            while (rs.next()) {
                mensajeUsuario = new RespuestaMensajesUsuario();
                mensajeUsuario.fecha_mensaje_usuario = rs.getTimestamp("fecha_mensaje_usuario");
                mensajeUsuario.mensaje_usuario = rs.getString("mensaje_usuario");
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                String username_destinatario = usuarioDAO.obtenerUsernameUsuario(rs.getInt("destinatario_usuario"));
                String username_remitente = usuarioDAO.obtenerUsernameUsuario(rs.getInt("remitente_usuario"));
                mensajeUsuario.username_destinatario = username_destinatario;
                mensajeUsuario.username_remitente = username_remitente;
                listaMensajesUsuario.add(mensajeUsuario);
            }
        } 
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return listaMensajesUsuario;
}

    
    public ArrayList<RespuestaMensajesAmigo> obtenerMensajesAmigo(int usuario, int amigo_mensaje) {
        ArrayList<RespuestaMensajesAmigo> listaMensajesAmigo = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement("SELECT * FROM mensaje_amigo where (remitente_amigo = ? AND destinatario_amigo = ?) OR (remitente_amigo = ? AND destinatario_amigo = ?)");
            ps.setInt(1, usuario);
            ps.setInt(2, amigo_mensaje);
            ps.setInt(3, amigo_mensaje);
            ps.setInt(4, usuario);
            RespuestaMensajesAmigo mensajeAmigo;
            rs = ps.executeQuery();
            while (rs.next()) {
                mensajeAmigo = new RespuestaMensajesAmigo();
                mensajeAmigo.fecha_mensaje_amigo = rs.getTimestamp("fecha_mensaje_amigo");
                mensajeAmigo.mensaje_amigo = rs.getString("mensaje_amigo");
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                String username_destinatario = usuarioDAO.obtenerUsernameUsuario(rs.getInt("destinatario_amigo"));
                String username_remitente = usuarioDAO.obtenerUsernameUsuario(rs.getInt("remitente_amigo"));
                mensajeAmigo.username_destinatario = username_destinatario;
                mensajeAmigo.username_remitente = username_remitente;
                listaMensajesAmigo.add(mensajeAmigo);
            }
        } catch(SQLException es) {
            System.out.println(es.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (getConnection() != null) {
                    getConnection().close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return listaMensajesAmigo;
    }

    
    public ArrayList<RespuestaMensajesGrupo> obtenerMensajesGrupo(int grupo_mensaje)
    {
        ArrayList<RespuestaMensajesGrupo> listaMensajesGrupo = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try 
        {
            ps = getConnection().prepareStatement("SELECT * FROM mensaje_grupo where destinatario_grupo = ?");
            ps.setInt(1, grupo_mensaje);
            rs = ps.executeQuery(); 

            while (rs.next())
            {
                RespuestaMensajesGrupo mensajeGrupo = new RespuestaMensajesGrupo();
                mensajeGrupo.fecha_mensaje_grupo = rs.getTimestamp("fecha_mensaje_grupo");
                mensajeGrupo.mensaje_grupo = rs.getString("mensaje_grupo");
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                String username_remitente = usuarioDAO.obtenerUsernameUsuario(rs.getInt("remitente_grupo"));
                mensajeGrupo.username_remitente = username_remitente;
                listaMensajesGrupo.add(mensajeGrupo);
            }            
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        } 
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return listaMensajesGrupo;
    }


}

