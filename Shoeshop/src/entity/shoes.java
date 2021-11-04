/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author user
 */
    public class shoes implements Serializable{
    private String shoesName;
    private String brand;
    private int quantity;
    private int price;

    public shoes() {
    }

    public String getShoesName() {
        return shoesName;
    }

    public void setShoesName(String shoesName) {
        this.shoesName = shoesName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }



    @Override
    public String toString() {
        return "Shoes{" 
                + "shoesName=" + shoesName 
                + ", brand=" + brand
                + ", quantity=" + quantity 
                + ", price=" + price 
                + '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
    
}