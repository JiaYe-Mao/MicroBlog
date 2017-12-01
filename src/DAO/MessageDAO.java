package DAO;

import model.Message;

import java.util.List;

public interface MessageDAO {
    List<Message> getMessage(Message message);
    void addMessage(Message message);
    void deleteMessage(Message message);
}
