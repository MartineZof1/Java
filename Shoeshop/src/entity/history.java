/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author user
 */
public class history implements Serializable{
    private customers customers;
    private shoes shoes;
    private Date givenDate;
    private Date returnedDate;

    public history() {
    }

    public customers getCustomers() {
        return customers;
    }

    public void setCustomers(customers customers) {
        this.customers = customers;
    }

    public shoes getShoes() {
        return shoes;
    }

    public void setShoes(shoes shoes) {
        this.shoes = shoes;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "history{" 
                + "customers=" + customers 
                + ", shoes=" + shoes 
                + ", givenDate=" + givenDate 
                + ", returnedDate=" + returnedDate 
                + '}';
    }
    
}