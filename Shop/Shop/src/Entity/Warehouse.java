/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author user
 */
public class Warehouse implements Serializable{
    private Customer customer;
    private Item item;
    private Date soldDate;
    private double soldTotal;

    
    
    
    public Warehouse() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }
    
    public double getSoldTotal() {
        return soldTotal;
    }

    public void setSoldTotal(double soldTotal) {
        this.soldTotal = soldTotal;
    }

@Override
    public String toString() {
        return "soldTotal = " + soldTotal ;
    }


}
