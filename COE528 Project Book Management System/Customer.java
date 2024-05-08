/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528_project;

import java.util.ArrayList;

/**
 *
 * @author Rayyan Bilal
 */
public class Customer {

    private int points;
    private String status;
    private String password;
    private String username;

    public void Customer(){}
    public Customer(String username, String password, int points){ 
        this.username = username; 
        this.password = password;
        this.points = points;
        
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean buyBooks(Book inBook) {
        //Array list implementation of removing a book and adding it to a cart arraylist maybe 
        return true; // only so there are no errors 
    }

    public boolean redeemAndBuy(Book inBook) {
        //bookPrice - points (100 points redeemed = 1 dollar) = remaining price 
        //Remember to add statement to check and change status if needed
        return true; // for no errors
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    
    

}
