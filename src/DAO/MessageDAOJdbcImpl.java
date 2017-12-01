package DAO;

import model.Message;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOJdbcImpl implements MessageDAO {
    private DataSource dataSource;

    public MessageDAOJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public List<Message> getMessage(Message message) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        List<Message> messages = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT date, txt FROM t_account WHERE name = ?");
            stmt.setString(1, message.getUsername());
            ResultSet rs = stmt.executeQuery();
            messages = new ArrayList<>();

            while (rs.next()){
                messages.add(new Message(message.getUsername(),
                        rs.getTimestamp(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            ex = e;
        } finally {
            connectionClose(stmt, conn, ex);
        }
        return messages;
    }

    @Override
    public void addMessage(Message message) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO t_message(name, date, txt) VALUES (?, ?, ?)");
            stmt.setString(1, message.getUsername());
            stmt.setTimestamp(2, new Timestamp(message.getDate().getTime()));
            stmt.setString(3, message.getTxt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            ex = e;
        } finally {
            connectionClose(stmt, conn, ex);
        }
    }

    @Override
    public void deleteMessage(Message message) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "DELETE FROM t_message WHERE date = ?");
            stmt.setTimestamp(1, new Timestamp(message.getDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            ex = e;
        } finally {
            connectionClose(stmt, conn, ex);
        }

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
