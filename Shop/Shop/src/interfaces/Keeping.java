/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Entity.Customer;
import Entity.Item;
import Entity.Warehouse;
import java.util.List;

/**
 *
 * @author user
 */
public interface Keeping {
    public void saveItems(List<Item> items);
    public List<Item> loadItems();
    public void saveCustomers(List<Customer> customers);
    public List<Customer> loadCustomers();
    public void saveWarehouseHistory(List<Warehouse> warehouseHistory);
    public List<Warehouse> loadWarehouseHistory();
}
