package nl.workshop2.dao;

import java.util.ArrayList;
import nl.workshop2.domain.Account;

/**
 *
 * @author Vosjes
 */
public interface AccountDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLAccountDAO geïmplementeerd worden
    */
    
    public boolean insertAccount(Account account);
    public Account selectAccount(int id);
    public Account selectAccount(String username);
    public ArrayList<Account> selectAccounts();
    public boolean updateAccount(Account account);
    public boolean deleteAccount(int id);
}
