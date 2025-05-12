package db_conection;

/**
 *
 * @author panch
 */
public class Amistad {
    public int id_amistad;
    public int amigo1;
    public int amigo2;
    
    public Amistad()
    {
        id_amistad = 0;
        amigo1 = 0;
        amigo2 = 0;
    }
    
    public Amistad(int amigo1, int amigo2)
    {
        this.id_amistad = id_amistad;
        this.amigo1 = amigo1;
        this.amigo2 = amigo2;
    }
}
