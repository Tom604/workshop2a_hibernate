package nl.workshop2.dao.mysqldao;

import nl.workshop2.dao.AccountDAO;
import nl.workshop2.dao.AdresDAO;
import nl.workshop2.dao.ArtikelDAO;
import nl.workshop2.dao.BestellingDAO;
import nl.workshop2.dao.BestelregelDAO;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.dao.KlantDAO;

/**
 *
 * @author Vosjes
 */
public class MySQLDAOFactory extends DAOFactory {
    
    /*
    Creatiemethodes voor alle DAO's in de factory
    */
    
    @Override
    public AccountDAO getAccountDAO() {
        return new MySQLAccountDAO();
    }
    
    @Override
    public AdresDAO getAdresDAO() {
        return new MySQLAdresDAO();
    }
    
    @Override
    public ArtikelDAO getArtikelDAO() {
        return new MySQLArtikelDAO();
    }
    
    @Override
    public BestellingDAO getBestellingDAO() {
        return new MySQLBestellingDAO();
    }
    
    @Override
    public BestelregelDAO getBestelregelDAO() {
        return new MySQLBestelregelDAO();
    }
    
    @Override
    public KlantDAO getKlantDAO() {
        return new MySQLKlantDAO();
    }
}
