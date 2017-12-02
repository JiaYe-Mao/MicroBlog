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
    private MailCarrier mailCarrier;
    private String template;

    public UserService(AccountDAO accountDAO, MessageDAO messageDAO, MailCarrier mailCarrier){
        this.accountDAO = accountDAO;
        this.messageDAO = messageDAO;
        this.mailCarrier = mailCarrier;
    }

    /*设置发送密码找回邮件的内容*/
    public void setTemplate(String template){
        this.template = template;
    }

    /*发送邮件*/
    public boolean sendPasswordTo (Account account){
        Account resultAccount = accountDAO.getAccount(account);
        if (resultAccount!=null && resultAccount.getEmail().equals(account.getEmail())){
            String subject = account.getUsername() + " 的微博密码";
            String content = null;
            if (template == null){
                content = account.getUsername() + " 您好! 您的密码是 " + account.getPassword();
            } else {
                content = template.replace("#name", account.getUsername())
                        .replace("#password", account.getPassword());
            }
            mailCarrier.sendTo(account, subject, content);
            return true;
        }
        return false;
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
