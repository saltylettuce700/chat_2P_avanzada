package returned_models;

import java.io.Serializable;

public class SolicitarMensajesUsuario  implements Serializable {
    public String usuarioLoggeado;
    public String usuarioSeleccionado;

    public SolicitarMensajesUsuario(String usuarioLoggeado, String usuarioSeleccionado)
    {
        this.usuarioLoggeado = usuarioLoggeado;
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

}
