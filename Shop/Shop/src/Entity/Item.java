/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;


public class Item implements Serializable{
    private String itemName;
    private double itemPrice;
    private int itemsInStock;

    
        
    
    public Item() {
        
    }

    
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemsInStock() {
        return itemsInStock;
    }

    public void setItemsInStock(int itemsInStock) {
        this.itemsInStock = itemsInStock;
    }

    @Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + ", itemPrice=$ " + itemPrice+ ", itemsInStock=" + itemsInStock + '}';
    }
}
