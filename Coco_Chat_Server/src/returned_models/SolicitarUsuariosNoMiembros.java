package returned_models;

import java.io.Serializable;

public class SolicitarUsuariosNoMiembros implements Serializable{
    public String UsuarioLoggeado;
    public String NombreGrupo;
    
    public SolicitarUsuariosNoMiembros(String UsuarioLoggeado, String NombreGrupo)
    {
        this.UsuarioLoggeado = UsuarioLoggeado;
        this.NombreGrupo = NombreGrupo;
    }
}
