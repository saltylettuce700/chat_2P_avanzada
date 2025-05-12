/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author panch
 */
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
