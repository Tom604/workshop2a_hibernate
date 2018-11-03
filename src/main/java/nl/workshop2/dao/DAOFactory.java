package nl.workshop2.dao;

import nl.workshop2.dao.mysqldao.MySQLDAOFactory;
import nl.workshop2.utility.DatabaseConnection;

/**
 *
 * @author Vosjes
 */
public abstract class DAOFactory {
    
    /*
    Hier bevinden zich de abstracte methodes die door de factories geïmplementeerd
    dienen te worden, alsook de statische methode om de betreffende factory te maken.
    Gebruiken bij meerdere persistence manieren (bijv. MySQL en MongoDB)
    */
    
    public abstract AccountDAO getAccountDAO();
    public abstract AdresDAO getAdresDAO();
    public abstract ArtikelDAO getArtikelDAO();
    public abstract BestellingDAO getBestellingDAO();
    public abstract BestelregelDAO getBestelregelDAO();
    public abstract KlantDAO getKlantDAO();
    
    public static DAOFactory getDAOFactory() {
        
        int db = DatabaseConnection.getDatabaseType();
        
        switch (db) {
            case 1: return new MySQLDAOFactory();
            // case 2: return new MongoDBDAOFactory();
            // Eventueel meerdere cases voor meerdere databases
            
            default: return null;
        }
    }
}
