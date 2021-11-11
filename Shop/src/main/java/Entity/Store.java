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
public class Store implements Serializable{
    private Customer customer;
    private Shoe shoe;
    private Date soldDate;
    private double soldTotal;

    
    
    
    public Store() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
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