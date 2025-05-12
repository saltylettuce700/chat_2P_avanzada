package db_conection_package;

import static java.lang.String.valueOf;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import returned_models.*;

public class UsuarioDAO extends Db_Conection{
    public UsuarioDAO()
    {
        super();
    }
    
    public boolean RegistrarUsuario(Usuario usuario) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement("INSERT INTO usuario (nombre, username, password, correo, pregunta_respaldo, estado) values (?,?,?,?,?,?)");

            ps.setString(1, usuario.nombre);
            ps.setString(2, usuario.username);
            ps.setString(3, usuario.password);
            ps.setString(4, usuario.correo);
            ps.setString(5, usuario.pregunta_respaldo);
            ps.setInt(6, usuario.estado);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // Manejar la excepción de cierre
            }
        }
        return false;
    }
    
    public boolean IniciarSesion(String username, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConnection().prepareStatement("SELECT * FROM usuario WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery(); 

            //Si las credenciales son correctas
            if(rs.next()) {
                return true;
            } else {
                //Si las credenciales son incorrectas
                return false;
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
            } catch (SQLException e) {
                // Manejar la excepción de cierre
            }
        }
        return false;
    }
    
    public boolean RecuperarCuentaValidar(String username, String respuesta)
    {
        try 
        {
            PreparedStatement ps =  getConnection().prepareStatement("SELECT * FROM usuario WHERE username=? AND pregunta_respaldo=?");
            ps.setString(1, username);
            ps.setString(2, respuesta);
            ResultSet rs = ps.executeQuery(); 

            //Si las credenciales son correctas
            if(rs.next())
            {
                rs.close();
                ps.close();
                return true;
            }
            //Si las credenciales son incorrectas
            else
            {
                rs.close();
                ps.close();
                return false;
            }          
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        return false;
    }
    
    public boolean CambiarContrasena(String username, String password)
    {
        int res = 0;
        try 
        {
            PreparedStatement ps = getConnection()
                                .prepareStatement
                                ("UPDATE usuario SET password = ? WHERE username = ?");
            
            ps.setString(1, password);
            ps.setString(2, username);
            
            res = ps.executeUpdate();
            ps.close(); // Cerrar el PreparedStatement
            getConnection().close(); // Cerrar la conexión
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return res>0;
    }
    
    public String obtenerUsernameUsuario(int id)
    {
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT username FROM usuario WHERE id_usuario=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                String username = rs.getString("username");
                ps.close();
                rs.close();
                return username;
            }
            ps.close();
            rs.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public String obtenerNombreUsuario(int id)
    {
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT nombre FROM usuario WHERE id_usuario=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                String nombre = rs.getString("nombre");
                rs.close(); // Cerrar ResultSet
                ps.close(); // Cerrar PreparedStatement
                return nombre;
            }
            else {
                rs.close(); // Cerrar ResultSet
                ps.close(); // Cerrar PreparedStatement
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public int ObtenerIDUsuario(String username)
    {
        try 
        {
            PreparedStatement ps =  getConnection().prepareStatement("SELECT id_usuario FROM usuario WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery(); 

            //Si las credenciales son correctas
            if(rs.next())
            {
                //Aqui necesitamos poner esta variable global o en algun cache para guardarla
                int id_usuario = rs.getInt("id_usuario");
                rs.close(); // Cerrar ResultSet
                ps.close(); // Cerrar PreparedStatement
                return id_usuario;
            }  
            else {
                rs.close(); // Cerrar ResultSet
                ps.close(); // Cerrar PreparedStatement
            }
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        return -1;
    }
    
    public ArrayList<Usuario> obtenerUsuarios(String username)
    {
        ArrayList<Usuario> listaUsuarios = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try 
        {
            ps = getConnection().prepareStatement("SELECT * FROM usuario WHERE username <> ?");
            ps.setString(1, username);
            rs = ps.executeQuery(); 
            Usuario usuario;

            while (rs.next())
            {
                usuario = new Usuario();
                usuario.nombre = rs.getString("nombre");
                usuario.username = rs.getString("username");
                usuario.estado = rs.getInt("estado");
                listaUsuarios.add(usuario);
            }            
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        finally
        {
            // Cerrar ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Manejo de excepciones
                }
            }

            // Cerrar PreparedStatement
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // Manejo de excepciones
                }
            }
        }
        return listaUsuarios;
    }
    
    public ArrayList<Usuario> obtenerAmigos(int usuario)
    {
        ArrayList<Usuario> listaAmigos = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try 
        {
            ps = getConnection().prepareStatement("SELECT * FROM amistad where (amigo1 = ? OR amigo2 = ?)");
            ps.setInt(1, usuario);
            ps.setInt(2, usuario);

            Amistad infoAmistad;
            rs = ps.executeQuery(); 

            while (rs.next())
            {
                infoAmistad = new Amistad();
                infoAmistad.amigo1 = rs.getInt("amigo1");
                infoAmistad.amigo2 = rs.getInt("amigo2");
                if(usuario != infoAmistad.amigo1)
                {
                    String username = obtenerUsernameUsuario(infoAmistad.amigo1);
                    String nombre = obtenerNombreUsuario(infoAmistad.amigo1);
                    Usuario infoAmigo = new Usuario();
                    infoAmigo.nombre = nombre;
                    infoAmigo.username = username;
                    listaAmigos.add(infoAmigo);
                }
                else if(usuario != infoAmistad.amigo2)
                {
                    String username = obtenerUsernameUsuario(infoAmistad.amigo2);
                    String nombre = obtenerNombreUsuario(infoAmistad.amigo2);
                    Usuario infoAmigo = new Usuario();
                    infoAmigo.nombre = nombre;
                    infoAmigo.username = username;
                    listaAmigos.add(infoAmigo);
                }
            }           
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        finally
        {
            // Cerrar ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Manejo de excepciones
                }
            }

            // Cerrar PreparedStatement
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // Manejo de excepciones
                }
            }
        }
        return listaAmigos;
    }
    
public ArrayList<Usuario> obtenerNoAmigos(int usuario)
{
    ArrayList<Usuario> listaNoAmigos = new ArrayList();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try 
    {
        ps = getConnection().prepareStatement("SELECT * FROM usuario WHERE id_usuario <> ? AND id_usuario NOT IN (SELECT amigo2 FROM amistad WHERE amigo1 = ? UNION SELECT amigo1 FROM amistad WHERE amigo2 = ? UNION SELECT destinatario_solicitud_amistad FROM solicitud_amistad WHERE remitente_solicitud_amistad = ?)");
        ps.setInt(1, usuario);
        ps.setInt(2, usuario);
        ps.setInt(3, usuario);
        ps.setInt(4, usuario);

        rs = ps.executeQuery(); 

        while (rs.next())
        {
            Usuario infoNoAmigo = new Usuario();
            infoNoAmigo.nombre = rs.getString("nombre");
            infoNoAmigo.username = rs.getString("username");
            listaNoAmigos.add(infoNoAmigo);
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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    return listaNoAmigos;
}
    
    public ArrayList<Grupo> obtenerGrupos(int usuario)
    {
        ArrayList<Grupo> listaGrupos = new ArrayList();
        try 
        {
            PreparedStatement ps =  getConnection().prepareStatement("SELECT * FROM pertenencias_grupo where usuario_perteneciente = ?");
            ps.setInt(1, usuario);

            ResultSet rs;
            ResultSet rs2;
            Grupo infoGrupo;
            rs = ps.executeQuery(); 

            while (rs.next()) {
                int ID_grupo = rs.getInt("grupo");
                PreparedStatement ps2 = getConnection().prepareStatement("SELECT * FROM grupo where id_grupo = ?");
                ps2.setInt(1, ID_grupo);
                rs2 = ps2.executeQuery();
                infoGrupo = new Grupo();
                if (rs2.next()) {
                    infoGrupo.nombre_grupo = rs2.getString("nombre_grupo");
                    listaGrupos.add(infoGrupo);
                }
                try {
                    rs2.close();
                } catch (SQLException e) {
                }
                try {
                    ps2.close();
                } catch (SQLException e) {
                }
            }       
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        return listaGrupos;
    }
    
    public ArrayList<Grupo> obtenerGruposPropietario(int usuario)
    {
        ArrayList<Grupo> listaGruposPropietario = new ArrayList();
        try 
        {
            PreparedStatement ps =  getConnection().prepareStatement("SELECT * FROM grupo where creador_grupo = ?");
            ps.setInt(1, usuario);

            ResultSet rs;
            ResultSet rs2;
            Grupo infoGrupo;
            rs = ps.executeQuery(); 

            while (rs.next()) {
                int ID_grupo = rs.getInt("id_grupo");
                PreparedStatement ps2 = getConnection().prepareStatement("SELECT * FROM grupo where id_grupo = ?");
                ps2.setInt(1, ID_grupo);
                rs2 = ps2.executeQuery();
                infoGrupo = new Grupo();
                if (rs2.next()) {
                    infoGrupo.nombre_grupo = rs2.getString("nombre_grupo");
                    listaGruposPropietario.add(infoGrupo);
                }
                try {
                    rs2.close();
                } catch (SQLException e) {
                }
                try {
                    ps2.close();
                } catch (SQLException e) {
                }
            }       
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        return listaGruposPropietario;
    }
    
    public boolean ConectarUsuario(String username)
    {
        int res = 0;
        try 
        {
            PreparedStatement ps = getConnection().prepareStatement("UPDATE usuario SET estado = 1 WHERE username = ?");
            ps.setString(1, username);
            res = ps.executeUpdate();
            ps.close(); // Close the PreparedStatement object
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return res > 0;
    }
   
    public boolean DesconectarUsuario(String username)
    {
        int res = 0;
        try 
        {
            PreparedStatement ps = getConnection()
                                .prepareStatement
                                ("UPDATE usuario SET estado = 0 WHERE username = ?");

            ps.setString(1, username);

            res = ps.executeUpdate();

            ps.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return res>0;
    }
    
    public ArrayList<InfoSolicitudAmistad> obtenerSolicitudesAmistad(int usuario)
    {
        ArrayList<InfoSolicitudAmistad> listaSolicitudesAmistad = new ArrayList();
        try 
        {
            PreparedStatement ps =  getConnection().prepareStatement("SELECT * FROM solicitud_amistad where destinatario_solicitud_amistad = ?");
            ps.setInt(1, usuario);

            ResultSet rs;
            InfoSolicitudAmistad infoSolicitudAmistad;
            rs = ps.executeQuery(); 

            String username_destinatario = obtenerUsernameUsuario(usuario);

            while (rs.next())
            {
                infoSolicitudAmistad = new InfoSolicitudAmistad();
                String username_remitente = obtenerUsernameUsuario(rs.getInt("remitente_solicitud_amistad"));
                infoSolicitudAmistad.remitente_solicitud_amistad = username_remitente;
                infoSolicitudAmistad.destinatario_solicitud_amistad = username_destinatario;
                listaSolicitudesAmistad.add(infoSolicitudAmistad);
            } 

            rs.close();
            ps.close();          
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        return listaSolicitudesAmistad;
    }
    
    public ArrayList<InfoInvitacionGrupo> obtenerInvitacionesGrupos(int usuario)
    {
        ArrayList<InfoInvitacionGrupo> listaInvitacionesGrupos = new ArrayList();
        try 
        {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM invitacion_grupo where destinatario_invitacion_grupo = ?");
            ps.setInt(1, usuario);

            ResultSet rs = ps.executeQuery(); 
            InfoInvitacionGrupo infoInvitacionGrupo;
            String username_destinatario = obtenerUsernameUsuario(usuario);
            GruposDAO gruposDAO; 

            while (rs.next())
            {
                infoInvitacionGrupo = new InfoInvitacionGrupo();
                String username_remitente = obtenerUsernameUsuario(rs.getInt("remitente_invitacion_grupo"));
                gruposDAO = new GruposDAO();
                String nombre_grupo = gruposDAO.obtenerNombreGrupo(rs.getInt("id_grupo_invitado"));
                infoInvitacionGrupo.grupo_invitado = nombre_grupo;
                infoInvitacionGrupo.remitente_invitacion_grupo = username_remitente;
                infoInvitacionGrupo.destinatario_invitacion_grupo = username_destinatario;
                listaInvitacionesGrupos.add(infoInvitacionGrupo);
            }
            rs.close();
            ps.close();
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        return listaInvitacionesGrupos;
    }
   
}

