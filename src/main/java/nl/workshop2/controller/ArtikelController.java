package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.ArtikelDAO;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public class ArtikelController {

    public boolean insertArtikel(Artikel artikel) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.insertArtikel(artikel);
    }
    
    public Artikel selectArtikel(int id) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.selectArtikel(id);
    }
    
    public ArrayList<Artikel> selectArtikelen() {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.selectArtikelen();
    }
    
    public boolean updateArtikel(Artikel artikel) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.updateArtikel(artikel);
    }
    
    public boolean deleteArtikel(int id) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.deleteArtikel(id);
    }
}
