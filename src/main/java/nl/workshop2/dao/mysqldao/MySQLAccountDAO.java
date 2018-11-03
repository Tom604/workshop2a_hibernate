package nl.workshop2.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import nl.workshop2.dao.AccountDAO;
import nl.workshop2.domain.Account;
import nl.workshop2.domain.Klant;
import nl.workshop2.utility.HikariCPDataSource;
import nl.workshop2.utility.PasswordStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLAccountDAO implements AccountDAO {

    static Logger log = LoggerFactory.getLogger(MySQLAccountDAO.class);
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLAccountDAO object
    */
    
    @Override
    public boolean insertAccount(Account account) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through insertAccount");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO account (username, wachtwoord, accounttype, klant_id) " +
                    "VALUES (?, ?, ?, (SELECT id FROM klant WHERE id = ?))");
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, PasswordStorage.createHash(account.getWachtwoord()));
            preparedStatement.setString(3, account.getAccounttype());
            if (account.getKlant() != null) {
                preparedStatement.setInt(4, account.getKlant().getId());
            }
            else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
                
            updateExecuted = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertAccount");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public Account selectAccount(int id) {
        
        Account account = new Account();
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectAccount-id");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, username, wachtwoord, accounttype, klant_id " +
                    "FROM account WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setWachtwoord(resultSet.getString("wachtwoord"));
                account.setAccounttype(resultSet.getString("accounttype"));
                if (resultSet.getInt("klant_id") != 0){
                    Klant klant = new Klant();
                    klant.setId(resultSet.getInt("klant_id"));
                    account.setKlant(klant);
                }
                else {
                    account.setKlant(null);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAccount-id");
        }
        
        return account;
    }
    
    @Override
    public Account selectAccount(String username) {
        
        Account account = new Account();
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectAccount-username");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, username, wachtwoord, accounttype, klant_id " +
                    "FROM account WHERE username = ?");
            preparedStatement.setString(1, username);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setWachtwoord(resultSet.getString("wachtwoord"));
                account.setAccounttype(resultSet.getString("accounttype"));
                if (resultSet.getInt("klant_id") != 0){
                    Klant klant = new Klant();
                    klant.setId(resultSet.getInt("klant_id"));
                    account.setKlant(klant);
                }
                else {
                    account.setKlant(null);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAccount-username");
        }
        
        return account;
    }
    
    @Override
    public ArrayList<Account> selectAccounts() {
        
        ArrayList<Account> accounts = new ArrayList<>();
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectAccounts");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, username, wachtwoord, accounttype, klant_id " +
                    "FROM account");
        
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setWachtwoord(resultSet.getString("wachtwoord"));
                account.setAccounttype(resultSet.getString("accounttype"));
                if (resultSet.getInt("klant_id") != 0){
                    Klant klant = new Klant();
                    klant.setId(resultSet.getInt("klant_id"));
                    account.setKlant(klant);
                }
                else {
                    account.setKlant(null);
                }
                accounts.add(account);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAccounts");
        }
        
        return accounts;
    }

    @Override
    public boolean updateAccount(Account account) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through updateAccount");
            
            preparedStatement = connection.prepareStatement(
                    "UPDATE account SET username = ?, wachtwoord = ?, " +
                    "accounttype = ?, klant_id = ? WHERE id = ?");
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, PasswordStorage.createHash(account.getWachtwoord()));
            preparedStatement.setString(3, account.getAccounttype());
            preparedStatement.setInt(4, account.getKlant().getId());
            preparedStatement.setInt(5, account.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateAccount");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public boolean deleteAccount(int id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through deleteAccount-id");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM account WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteAccount-id");
        }
        
        return updateExecuted != 0;
    }
}
