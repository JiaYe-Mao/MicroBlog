package model;

import model.Account;

public interface MailCarrier {
    void sendTo(Account account, String subject, String content);
}
