package db_conection_package;

public class Pertenencias_Grupo {
    public int id_pertenencia_grupo;
    public int grupo;
    public int usuario_perteneciente;
    
    public Pertenencias_Grupo()
    {
        id_pertenencia_grupo = 0;
        grupo = 0;
        usuario_perteneciente = 0;
    }
    
    public Pertenencias_Grupo(int grupo, int usuario_perteneciente)
    {
        this.id_pertenencia_grupo = id_pertenencia_grupo;
        this.grupo = grupo;
        this.usuario_perteneciente = usuario_perteneciente;
    }
}

