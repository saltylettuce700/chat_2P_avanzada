/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author panch
 */
public class SolicitudCrearGrupo implements Serializable{
    public String creador_grupo;
    public String nombre_grupo;
    public ArrayList<String> usuarios_invitados;

    public SolicitudCrearGrupo(String creador_grupo, String nombre_grupo, ArrayList<String> usuarios_invitados)
    {
        this.usuarios_invitados = usuarios_invitados;
        this.creador_grupo = creador_grupo;
        this.nombre_grupo = nombre_grupo;
    }

}
