package model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
    private String username;
    private Date date;
    private String txt;

    public Message(){ }

    public Message(String username, Date date, String txt) {
        this.username = username;
        this.date = date;
        this.txt = txt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (username != null ? !username.equals(message.username) : message.username != null) return false;
        if (date != null ? !date.equals(message.date) : message.date != null) return false;
        return txt != null ? txt.equals(message.txt) : message.txt == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (txt != null ? txt.hashCode() : 0);
        return result;
    }
}
