/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.shoes;
import entity.history;
import entity.customers;
import java.util.List;

/**
 *
 * @author user
 */
public interface Keeping {
    public void saveShoes(List<shoes> shoes);
    public List<shoes> loadShoes();
    public void saveCustomers(List<customers> customers);
    public List<customers> loadCustomers();
    public void saveHistories(List<history> histories);
    public List<history> loadHistories();
}