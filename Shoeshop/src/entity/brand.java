/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class brand implements Serializable{
    private String name;

    public brand() {
    }

    public String getFirstname() {
        return name;
    }

    public void setFirstname(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "brand{" + "name=" + name +'}';
    }
    
    
}