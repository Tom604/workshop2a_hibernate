package nl.workshop2.controller;

import java.util.ArrayList;
import nl.workshop2.dao.AccountDAO;
import nl.workshop2.dao.DAOFactory;
import nl.workshop2.domain.Account;
import nl.workshop2.utility.PasswordStorage;

/**
 *
 * @author Vosjes
 */
public class AccountController {
    
    public boolean insertAccount(Account account) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.insertAccount(account);
    }
    
    public Account selectAccount(int id) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.selectAccount(id);
    }
    
    public ArrayList<Account> selectAccounts() {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.selectAccounts();
    }
    
    public boolean updateAccount(Account account) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.updateAccount(account);
    }
    
    public boolean deleteAccount(int id) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.deleteAccount(id);
    }
    
    public boolean validatePassword(String wachtwoord) {
        
        boolean validation = false;
        
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        Account account = accountDAO.selectAccount(LoginController.loginnaam);
        if (PasswordStorage.verifyPassword(wachtwoord, account.getWachtwoord())) {
            validation = true;
        }
        return validation;
    }
}
