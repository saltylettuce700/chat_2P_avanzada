package db_conection_package;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import returned_models.*;

public class GruposDAO extends Db_Conection{
    public GruposDAO()
    {
        super();
    }
    
    public boolean CrearGrupo(String nombre_grupo, int creador_grupo, ArrayList<String> usuarios_a_invitar) {
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO grupo (nombre_grupo, creador_grupo) values (?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nombre_grupo);
            ps.setInt(2, creador_grupo);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int id_grupo = -1;
            if (rs.next()) {
                id_grupo = rs.getInt(1);
            }
            rs.close();
            ps.close();

            PreparedStatement ps2 = getConnection().prepareStatement("INSERT INTO pertenencias_grupo (grupo, usuario_perteneciente) values (?,?)");
            ps2.setInt(1, id_grupo);
            ps2.setInt(2, creador_grupo);
            ps2.executeUpdate();
            ps2.close();

            for(String user : usuarios_a_invitar)
            {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                int IDUsuario = usuarioDAO.ObtenerIDUsuario(user);
                PreparedStatement ps3 = getConnection().prepareStatement("INSERT INTO invitacion_grupo (id_grupo_invitado, remitente_invitacion_grupo, destinatario_invitacion_grupo) values (?,?,?)");
                ps3.setInt(1, id_grupo);
                ps3.setInt(2, creador_grupo);
                ps3.setInt(3, IDUsuario);
                ps3.executeUpdate();
                ps3.close();
            }

            return true;
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean EnviarInvitacionGrupo(int id_grupo_invitado, int invitacion_de, int invitacion_para) {
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO invitacion_grupo (id_grupo_invitado, remitente_invitacion_grupo, destinatario_invitacion_grupo) values (?,?,?)");

            ps.setInt(1, id_grupo_invitado);
            ps.setInt(2, invitacion_de);
            ps.setInt(3, invitacion_para);

            boolean result = ps.executeUpdate() > 0;
            ps.close();
            return result;
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean AceptarInvitacionGrupo(int id_grupo, int usuario_invitado) {
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO pertenencias_grupo (grupo, usuario_perteneciente) values (?,?)");

            ps.setInt(1, id_grupo);
            ps.setInt(2, usuario_invitado);

            PreparedStatement ps2 = getConnection().prepareStatement("DELETE FROM invitacion_grupo where id_grupo_invitado = ? AND destinatario_invitacion_grupo = ?");
            ps2.setInt(1, id_grupo);
            ps2.setInt(2, usuario_invitado);
            ps2.executeUpdate();

            boolean result = ps.executeUpdate() > 0;
            ps.close();
            ps2.close();
            return result;
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean RechazarInvitacionGrupo(int id_grupo, int usuario_invitado) 
    {
        try
        {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM invitacion_grupo where id_grupo_invitado = ? AND destinatario_invitacion_grupo = ?");
            ps.setInt(1, id_grupo);
            ps.setInt(2, usuario_invitado);

            // Verificar cuantos usuarios en el grupo quedan y si quedan menos de 3, eliminarlo
            int numUsuarios = 0;
            PreparedStatement psCountPertenencias = getConnection().prepareStatement("SELECT COUNT(*) FROM pertenencias_grupo WHERE grupo = ?");
            psCountPertenencias.setInt(1, id_grupo);
            ResultSet rsCountPertenencias = psCountPertenencias.executeQuery();

            if (rsCountPertenencias.next()) {
                numUsuarios = rsCountPertenencias.getInt(1);
            }

            PreparedStatement psCountInvitaciones = getConnection().prepareStatement("SELECT COUNT(*) FROM invitacion_grupo  WHERE id_grupo_invitado = ?");
            psCountInvitaciones.setInt(1, id_grupo);
            ResultSet rsCountInvitaciones = psCountInvitaciones.executeQuery();

            if (rsCountInvitaciones.next()) {
                numUsuarios = numUsuarios + rsCountInvitaciones.getInt(1);
            }

            // Si hay menos de 3 usuarios, eliminar el grupo
            if (numUsuarios < 3) {
                EliminarGrupo(id_grupo);
            }

            ps.close();
            psCountPertenencias.close();
            psCountInvitaciones.close();
            return true;
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean SalirGrupo(int id_grupo, int usuario_invitado) {
        try {
            //Eliminar el usuario de pertenencias grupo
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM pertenencias_grupo where grupo = ? AND usuario_perteneciente = ?");
            ps.setInt(1, id_grupo);
            ps.setInt(2, usuario_invitado);

            // Verificar cuantos usuarios en el grupo quedan y si quedan menos de 3, eliminarlo
            int numUsuarios = 0;
            PreparedStatement psCountPertenencias = getConnection().prepareStatement("SELECT COUNT(*) FROM pertenencias_grupo WHERE grupo = ?");
            psCountPertenencias.setInt(1, id_grupo);
            ResultSet rsCountPertenencias = psCountPertenencias.executeQuery();

            if (rsCountPertenencias.next()) {
                numUsuarios = rsCountPertenencias.getInt(1);
            }

            PreparedStatement psCountInvitaciones = getConnection().prepareStatement("SELECT COUNT(*) FROM invitacion_grupo  WHERE id_grupo_invitado = ?");
            psCountInvitaciones.setInt(1, id_grupo);
            ResultSet rsCountInvitaciones = psCountInvitaciones.executeQuery();

            if (rsCountInvitaciones.next()) {
                numUsuarios = numUsuarios + rsCountInvitaciones.getInt(1);
            }

            // Si hay menos de 3 usuarios, eliminar el grupo
            if (numUsuarios < 3) {
                EliminarGrupo(id_grupo);
            }

            ps.close();
            psCountPertenencias.close();
            psCountInvitaciones.close();

            return true;
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

      
    public boolean EliminarGrupo(int id_grupo) {
        try {
            //Eliminar los usuarios pertenecientes
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM pertenencias_grupo where grupo = ?");
            ps.setInt(1, id_grupo);
            ps.executeUpdate();
            ps.close();

            //Eliminar las invitaciones
            PreparedStatement ps2 = getConnection().prepareStatement("DELETE FROM invitacion_grupo where id_grupo_invitado = ?");
            ps2.setInt(1, id_grupo);
            ps2.executeUpdate();
            ps2.close();

            //Eliminar los mensajes del grupo
            PreparedStatement ps3 = getConnection().prepareStatement("DELETE FROM mensaje_grupo where destinatario_grupo = ?");
            ps3.setInt(1, id_grupo);
            ps3.executeUpdate();
            ps3.close();

            //Eliminar el grupo
            PreparedStatement ps4 = getConnection().prepareStatement("DELETE FROM grupo where id_grupo = ?");
            ps4.setInt(1, id_grupo);
            boolean result = ps4.executeUpdate() > 0;
            ps4.close();
            return result;
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }


    public ArrayList<Usuario> obtenerNoMiembrosSinInvitacion(int idGrupo, int creadorGrupo) {
        ArrayList<Usuario> listaNoMiembros = new ArrayList();
        try {
            // Obtener lista de usuarios que no son miembros del grupo y no han sido invitados
            PreparedStatement ps = getConnection().prepareStatement(
                "SELECT * FROM usuario WHERE id_usuario != ? " + // Excluir al creador del grupo
                "AND id_usuario NOT IN (SELECT usuario_perteneciente FROM pertenencias_grupo WHERE grupo = ?) " +
                "AND id_usuario NOT IN (SELECT destinatario_invitacion_grupo FROM invitacion_grupo WHERE id_grupo_invitado = ?)"
            );
            ps.setInt(1, creadorGrupo);
            ps.setInt(2, idGrupo);
            ps.setInt(3, idGrupo);
            ResultSet rs = ps.executeQuery();

            // Filtrar lista para obtener solo los usuarios que no son amigos del creador del grupo
            while (rs.next()) {
                Usuario infoNoMiembro = new Usuario();
                infoNoMiembro.nombre = rs.getString("nombre");
                infoNoMiembro.username = rs.getString("username");
                listaNoMiembros.add(infoNoMiembro);
            }
        } catch (SQLException es) {
            System.out.println(es.getMessage());
        }
        return listaNoMiembros;
    }

    
    public int ObtenerIDGrupo(String grupo) {
        try {
                PreparedStatement ps =  getConnection().prepareStatement("SELECT id_grupo FROM grupo WHERE nombre_grupo=?");
                ps.setString(1, grupo);
                ResultSet rs = ps.executeQuery();

                //Si las credenciales son correctas
                if(rs.next()) {
                    //Aqui necesitamos poner esta variable global o en algun cache para guardarla
                    int id_grupo = rs.getInt("id_grupo");

                    rs.close();
                    ps.close();

                    return id_grupo;
                }

                rs.close();
                ps.close();

            } catch(SQLException es) {
                System.out.println(es.getMessage());
            }
            return -1;
        }

    
    public String obtenerNombreGrupo(int id_grupo)
    {
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT nombre_grupo FROM grupo WHERE id_grupo=?");
            ps.setInt(1,id_grupo);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                String nombre_grupo = rs.getString("nombre_grupo");
                ps.close();
                rs.close();
                return nombre_grupo;
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }


}

