/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Entity.Customer;
import Entity.Shoe;
import Entity.Store;
import java.util.List;

/**
 *
 * @author user
 */
public interface Keeping {
    public void saveShoes(List<Shoe> shoes);
    public List<Shoe> loadShoes();
    public void saveCustomers(List<Customer> customers);
    public List<Customer> loadCustomers();
    public void saveStoreHistory(List<Store> storeHistory);
    public List<Store> loadStoreHistory();
}