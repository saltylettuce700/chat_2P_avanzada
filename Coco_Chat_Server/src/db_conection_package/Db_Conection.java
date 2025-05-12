package db_conection_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_Conection {
    private Connection con;
    
    public Db_Conection()
    {
        try
        {
         Class.forName("com.mysql.cj.jdbc.Driver");
            
         con = DriverManager.getConnection("jdbc:mysql://localhost/coco_chat_bd","root","");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver no cargado: " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.out.println("Error de conexion a la bd: " + ex.getMessage());
        }
    }
    
    public Connection getConnection()
    {
        return con;
    }
}