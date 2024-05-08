/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528_project;

/**
 *
 * @author Rayyan Bilal
 */
public class Book {

    private String title;
    private double price;
    private int BookPoints;

    public String getBookTitle() {
        return title;
    }

    public void setBookTitle(String title) {
        this.title = title;
    }

    public double getBookPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBookPoints() {
        return BookPoints;
    }

    public void setBookPoints(int BookPoints) {
        this.BookPoints = BookPoints;
    }

}
