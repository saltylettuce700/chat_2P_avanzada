package returned_models;

import java.io.Serializable;

public class RespuestaRecuperarCuenta implements Serializable {
        public String resultadoFuncion;
        public String username;
        
        public RespuestaRecuperarCuenta()
        {
            resultadoFuncion = "";
            username = "";
        }
        
        public RespuestaRecuperarCuenta(String resultadoFuncion, String username)
        {
            this.resultadoFuncion = resultadoFuncion;
            this.username = username;
        }
}
