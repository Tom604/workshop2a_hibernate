package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.AdresDAO;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.domain.Adres;

/**
 *
 * @author Vosjes
 */
public class AdresController {
    
    public boolean insertAdres(Adres adres) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.insertAdres(adres);
    }
    
    public Adres selectAdres(int id) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.selectAdres(id);
    }
    
    public ArrayList<Adres> selectAdressen() {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.selectAdressen();
    }
    
    public ArrayList<Adres> selectAdressen(int klantId) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.selectAdressen(klantId);
    }
    
    public boolean updateAdres(Adres adres) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.updateAdres(adres);
    }
    
    public boolean deleteAdres(int id) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.deleteAdres(id);
    }
}
