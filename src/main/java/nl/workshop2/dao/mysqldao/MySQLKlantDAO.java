package nl.workshop2.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import nl.workshop2.dao.KlantDAO;
import nl.workshop2.domain.Klant;
import nl.workshop2.utility.HikariCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLKlantDAO implements KlantDAO {

    static Logger log = LoggerFactory.getLogger(MySQLKlantDAO.class);
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLKlantDAO object
    */
    
    @Override
    public Klant insertKlant(Klant klant) {
        
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through insertKlant");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO klant (voornaam, achternaam, tussenvoegsel) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getTussenvoegsel());
            
            preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                klant.setId(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertKlant");
        }
        
        return klant;
    }

    @Override
    public Klant selectKlant(int id) {
        
        Klant klant = new Klant();
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectKlant-id");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, voornaam, achternaam, tussenvoegsel FROM klant WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                klant.setId(resultSet.getInt("id"));
                klant.setVoornaam(resultSet.getString("voornaam"));
                klant.setAchternaam(resultSet.getString("achternaam"));
                klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectKlant-id");
        }
        
        return klant;
    }
    
    @Override
    public ArrayList<Klant> selectKlanten() {
        
        ArrayList<Klant> klanten = new ArrayList<>();
        Klant klant;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectKlanten");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, voornaam, achternaam, tussenvoegsel FROM klant");
            
            while (resultSet.next()) {
                klant = new Klant();
                klant.setId(resultSet.getInt("id"));
                klant.setVoornaam(resultSet.getString("voornaam"));
                klant.setAchternaam(resultSet.getString("achternaam"));
                klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
                klanten.add(klant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectKlanten");
        }
        
        return klanten;
    }
    
    @Override
    public ArrayList<Klant> selectKlanten(String achternaam) {
        
        ArrayList<Klant> klanten = new ArrayList<>();
        Klant klant;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectKlanten-achternaam");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, voornaam, achternaam, tussenvoegsel FROM klant WHERE achternaam = ?");
            preparedStatement.setString(1, achternaam);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                klant = new Klant();
                klant.setId(resultSet.getInt("id"));
                klant.setVoornaam(resultSet.getString("voornaam"));
                klant.setAchternaam(resultSet.getString("achternaam"));
                klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
                klanten.add(klant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectKlanten-achternaam");
        }
        
        return klanten;
    }

    @Override
    public boolean updateKlant(Klant klant) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through updateKlant");
            
            preparedStatement = connection.prepareStatement(
                    "UPDATE klant SET voornaam = ?, achternaam = ?, tussenvoegsel = ? WHERE id = ?");
            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getTussenvoegsel());
            preparedStatement.setInt(4, klant.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateKlant");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public boolean deleteKlant(int id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through deleteKlant-id");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM klant WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteKlant-id");
        }
        
        return updateExecuted != 0;
    }
}
