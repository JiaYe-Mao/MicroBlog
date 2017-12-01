package DAO;

import model.Account;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOJdbcImpl implements AccountDAO {
    private DataSource dataSource;

    public AccountDAOJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public boolean isUserExisted(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        boolean existed = false;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT COUNT(1) FROM t_account WHERE name = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                existed = (rs.getInt(1) == 1);
            }
        } catch (SQLException e) {
            ex = e;
        } finally {
            connectionClose(stmt, conn, ex);
        }
        return existed;
    }

    @Override
    public void addAccount(Account account) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO t_account(name, password, email) VALUES (?, ?, ?)");
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            ex = e;
        } finally {
            connectionClose(stmt, conn, ex);
        }

    }

    @Override
    public Account getAccount(Account account) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        Account resultAccount = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT password, email FROM t_account WHERE name = ?");
            stmt.setString(1, account.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                resultAccount = new Account(account.getUsername(),
                        rs.getString(1), rs.getString(2));
            }

        } catch (SQLException e) {
            ex = e;
        } finally {
            connectionClose(stmt, conn, ex);
        }
        return resultAccount;
    }

    private void connectionClose(PreparedStatement stmt, Connection conn, Exception ex){
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e){
                ex = e;
            }
        }


        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                ex = e;
            }
        }

        if (ex != null){
            throw new RuntimeException(ex);
        }
    }
}
