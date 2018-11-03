package nl.workshop2.dao;

import java.util.ArrayList;
import nl.workshop2.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public interface ArtikelDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLArtikelDAO geïmplementeerd worden
    */
    
    public boolean insertArtikel(Artikel artikel);
    public Artikel selectArtikel(int id);
    public Artikel selectArtikel(String naam);
    public ArrayList<Artikel> selectArtikelen();
    public boolean updateArtikel(Artikel artikel);
    public boolean deleteArtikel(int id);
    public boolean deleteArtikel(String naam);
}
