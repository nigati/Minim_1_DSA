package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;
import edu.upc.dsa.models.Object;
import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User>{

    private String name;
    private String surname;
    private String birthday;
    private String email;
    private String password;
    private double balance;
    private List<Object> listObjects = new ArrayList<>();
    public User() {
    }

    public User(String name, String surname, String birthday, String email, String password) {
        this();
        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthday);
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name=name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void addObject (Object obj){
        this.listObjects.add(obj);
    }
    public List<Object> getListObjects(){
        return listObjects;
    }

    public int compareTo(User other) {
        // If this' lastName and other's lastName are not comparably equivalent,
        // Compare this to other by comparing their last names.
        // Otherwise, compare this to other by comparing their first names
        int surnameCompare = surname.compareTo(other.surname);
        if (surnameCompare != 0) {
            return surnameCompare;
        } else {
            return name.compareTo(other.name);
        }
    }
}