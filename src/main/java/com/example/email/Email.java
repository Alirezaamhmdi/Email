package com.example.email;

import java.io.Serializable;
import java.util.Date;

public class Email  implements Serializable {
    static int baseId = 11111;
    private int ID;
    private String sender;
    private String receiver;
    private String title;
    private String text;
    private Date date;
    private boolean important;
    private boolean read;

    public Email(String sender, String receiver, String title, String text, Date date
            , boolean important, boolean read) {
        this.ID = baseId++;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.text = text;
        this.date = date;
        this.important = important;
        this.read = read;
    }
    public Email(String sender, String receiver, String title, String text, Date date) {
        this.ID = baseId++;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.text = text;
        this.date = date;
        this.important = false;
        this.read = false;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getDestinationEmail(String email){
        if (email.equals(receiver))
            return sender;
        return receiver;
    }
}
