package model;

import DAO.AccountDAO;
import DAO.MessageDAO;

import java.io.*;
import java.util.*;

public class UserService {

    /*这个newest实现的不好, 应该是遍历所有message来实现, 现在只是一条一条加上去,一旦服务器重启就会清空*/
    private LinkedList<Message> newest = new LinkedList<>();
    public List<Message> getNewest(){
        return newest;
    }

    private AccountDAO accountDAO;
    private MessageDAO messageDAO;

    public UserService(AccountDAO accountDAO, MessageDAO messageDAO){
        this.accountDAO = accountDAO;
        this.messageDAO = messageDAO;
    }

    public boolean isUsernameExisted (String username){
        return accountDAO.isUserExisted(username);
    }

    public void createUserData (Account account){
        accountDAO.addAccount(account);
    }

    public boolean checkLogin(Account account) throws IOException {
        if (account.getUsername() != null && account.getPassword() != null){
            Account resultAccount = accountDAO.getAccount(account);
            return resultAccount != null
                    && resultAccount.getPassword().equals(account.getPassword());
        }
        return false;
    }


    private class DateComparator implements Comparator<Message> {
        @Override
        public int compare(Message o1, Message o2) {
            return -o1.getDate().compareTo(o2.getDate());
        }
    }

    public List<Message> getMessage(Message message) throws IOException {    //可以改成只要username这一个参数
        List<Message> messages = messageDAO.getMessage(message);
        messages.sort(new DateComparator());
        return messages;
    }

    public void addMessage(Message message) throws IOException {
        messageDAO.addMessage(message);
        newest.addFirst(message);
        if (newest.size() > 20){
            newest.removeLast();
        }
    }

    public void deleteMessage(Message message){
        messageDAO.deleteMessage(message);
        newest.remove(message);
    }

}
