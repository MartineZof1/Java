/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;


public class Shoe implements Serializable{
    private String shoeName;
    private double shoePrice;
    private int shoesInStock;

    
        
    
    public Shoe() {
        
    }

    
    
    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }
    
    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }

    public int getShoesInStock() {
        return shoesInStock;
    }

    public void setShoesInStock(int shoesInStock) {
        this.shoesInStock = shoesInStock;
    }

    @Override
    public String toString() {
        return "Shoe{" + "shoeName=" + shoeName + ", shoePrice=$ " + shoePrice+ ", shoesInStock=" + shoesInStock + '}';
    }
}