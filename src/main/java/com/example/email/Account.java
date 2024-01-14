package com.example.email;
import java.io.Serializable;
import java.util.ArrayList;
public class Account implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    ArrayList<Integer> emails;
    public Account(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        emails = new ArrayList<>();
    }
    public String getFullName(){
        return firstName+" "+lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return firstName + "," + lastName + "," + email + "," + password ;
    }
}
