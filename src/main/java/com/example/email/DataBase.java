package com.example.email;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
    static ArrayList<Account> accounts = new ArrayList<>();
    static ArrayList<Email> emails = new ArrayList<>();
    static Account courentAccount=null;
    static Account findEmail(String email){
        for (Account a:accounts) {
            if (a.getEmail().equals(email))
                return a;
        }
        return null;
    }
    public static ArrayList<Email> getEmails(ArrayList<Integer> emails1){
        ArrayList<Email> list = new ArrayList<>();
        for (int i = 0; i < emails1.size() ; i++) {
            for (Email e:emails) {
                if (e.getID()==emails1.get(i))
                    list.add(e);
            }
        }
        return list;
    }
    //--------------------- File Accounts ----------------------
    public static void saveAccountsToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Accounts");
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeInt(accounts.size());
            for (Account s:accounts) {
                objectOutputStream.writeObject(s);
            }
        }
    }
    public static void readAccountsFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Accounts");
        try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            int count = objectInputStream.readInt();
            for (int i = 0; i < count; i++) {
                accounts.add((Account) objectInputStream.readObject());
            }
        }
    }
    //--------------------- File Emails ----------------------
    public static void saveEmailsToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Emails");
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeInt(emails.size());
            for (Email s:emails) {
                objectOutputStream.writeObject(s);
            }
        }
    }
    public static void readEmailsFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Emails");
        try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            int count = objectInputStream.readInt();
            for (int i = 0; i < count; i++) {
                Email.baseId++;
                emails.add((Email) objectInputStream.readObject());
            }
        }
    }
}
