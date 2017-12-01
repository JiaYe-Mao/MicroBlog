package DAO;

import model.Account;

public interface AccountDAO {
    boolean isUserExisted(String username);
    void addAccount(Account account);
    Account getAccount(Account account);
}
