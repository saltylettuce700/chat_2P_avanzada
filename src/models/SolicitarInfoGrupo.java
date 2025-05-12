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
public class SolicitarInfoGrupo implements Serializable {
    public String UsuarioLoggeado;
    public String NombreGrupo;
    
    public SolicitarInfoGrupo(String UsuarioLoggeado, String NombreGrupo)
    {
        this.UsuarioLoggeado = UsuarioLoggeado;
        this.NombreGrupo = NombreGrupo;
    }
}
