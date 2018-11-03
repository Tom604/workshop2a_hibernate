package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.BestellingDAO;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.domain.Bestelling;

/**
 *
 * @author Vosjes
 */
public class BestellingController {
    
    public Bestelling insertBestelling(Bestelling bestelling) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.insertBestelling(bestelling);
    }
    
    public Bestelling selectBestelling(int id) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.selectBestelling(id);
    }
    
    public ArrayList<Bestelling> selectBestellingen() {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.selectBestellingen();
    }
    
    public ArrayList<Bestelling> selectBestellingen(int klantId) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.selectBestellingen(klantId);
    }
    
    public boolean updateBestelling(Bestelling bestelling) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.updateBestelling(bestelling);
    }
    
    public boolean deleteBestelling(int id) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.deleteBestelling(id);
    }
}
