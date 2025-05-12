package returned_models;

import java.io.Serializable;

public class SolicitarInfoGrupo implements Serializable {
    public String UsuarioLoggeado;
    public String NombreGrupo;
    
    public SolicitarInfoGrupo(String UsuarioLoggeado, String NombreGrupo)
    {
        this.UsuarioLoggeado = UsuarioLoggeado;
        this.NombreGrupo = NombreGrupo;
    }
}
