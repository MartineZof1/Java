/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyClasses;

import Entity.Customer;
import Entity.Item;
import Entity.Warehouse;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToFiles;

public class App {
    
    private Scanner scanner =new Scanner(System.in);
    private List<Item> items = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Warehouse> warehouseHistory = new ArrayList<>();
    private SaverToFiles saverToFiles = new SaverToFiles();
    
    public App(){
        items = saverToFiles.loadItems();
        customers = saverToFiles.loadCustomers();
        warehouseHistory = saverToFiles.loadWarehouseHistory();
    }
    
    public void run(){
        
        String repeat = "r";
        
        do{
            
            System.out.println("Выберите номер задачи:");
            System.out.println("1: Закончить программу");
            System.out.println("2: Добавить новый продукт"); 
            System.out.println("3: Добавить количество продуктов");
            System.out.println("4: Список продаваемых продуктов"); 
            System.out.println("5: Добавить покупателя"); 
            System.out.println("6: Список зарегистрированных покупателей"); 
            System.out.println("7: Покупка покупателем продуктов"); 
            System.out.println("8: Доход магазина за все время работы"); 
            System.out.println("9: Добавить денег покупателю"); 
            
            
            int task = getInt();
            
            
            switch (task){
                
                case 1:
                    repeat = "q";
                    System.out.println("Пока!");
                    
                    break;
                
                case 2:
                    System.out.println("Добавить новый продукт: ");
                    addItem();
                    break;
                        
                case 3:
                    System.out.println("Добавить количество продуктов: ");
                    addItemsInStock();
                    break;
                             
                case 4:
                    System.out.println("Список продаваемых продуктов: ");
                    printListItems();
                    break;
                    
                case 5:
                    System.out.println("Добавить покупателя: ");
                    addCustomer();
                    break;
                    
                case 6:
                    System.out.println("Список зарегистрированных покупателей: ");
                    printListCustomers();
                    break;
                    
                case 7:
                    System.out.println("Покупка покупателем продуктов: ");
                    sellItem();
                    break;
                    
                case 8:
                    System.out.println("Доход магазина за все время работы: ");
                    printTotalSold();
                    break;
                    
                case 9:
                    System.out.println("Добавить денег покупателю: ");
                    addMoney();
                    break;

                
                default:
                    System.out.println("Выберите цифру из списка!");
            }
        }
        while("r".equals(repeat));
    
    }
    
    
    private double getDouble() {
        double number;
        do{
           String strNumber = scanner.nextLine();
           try {
               number = Double.parseDouble(strNumber);
               return number;
           } catch (NumberFormatException e) {
               System.out.println("Введено \""+strNumber+"\". Введите число ");
           }
       }while(true);
    }
    
    private double insertDouble(Set<Double> setDoubles) {
        double number=0.0;
        do{
           number = getDouble();
           if(setDoubles.contains(number)){
               break;
           }
           System.out.println("Попробуй еще раз: ");
       }while(true);
       return number; 
    }
    
    private int getInt() {
        int number;
        do{
           String strNumber = scanner.nextLine();
           try {
               number = Integer.parseInt(strNumber);
               return number;
           } catch (NumberFormatException e) {
               System.out.println("Введено \""+strNumber+"\". Введите целое число ");
           }
       }while(true);
    }
    
    private int insertInt(Set<Integer> setInts) {
        int number=0;
        do{
           number = getInt();
           if(setInts.contains(number)){
               break;
           }
           System.out.println("Попробуй еще раз: ");
       }while(true);
       return number; 
    }
    
    
    private void addCustomer() {
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setName(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Введите количество средств покупателя: ");
        customer.setMoney(getDouble());
        System.out.println("Покупатель инициирован: "+customer.toString());
        customers.add(customer);
        saverToFiles.saveCustomers(customers);
    }
    
    
    private void addItem() {
        Item item = new Item();
        System.out.print("Введите название продукта: ");
        item.setItemName(scanner.nextLine());
        System.out.print("Введите цену продукта: ");
        item.setItemPrice(getDouble());
        System.out.print("Введите количество продуктов: ");
        item.setItemsInStock(getInt());
        System.out.println("Продукт инициирован: "+item.toString());
        items.add(item);
        saverToFiles.saveItems(items);
    }
    
    private void addItemsInStock() {
       System.out.println("---- Добавить количество продуктов ----");
       Set<Integer> setNumbersItems = printListItems();
       if(setNumbersItems.isEmpty()){
           System.out.println("Нет такого продукта");
          return;
       }
       System.out.println("Выберите продукт: ");
       int numberItem= insertInt(setNumbersItems);
       
       
       System.out.println("Введите количество: ");
       int numberOfItems= getInt();
       
       Warehouse warehouse = new Warehouse();
       warehouse.setItem(items.get(numberItem - 1));
       
       warehouse.getItem().setItemsInStock(warehouse.getItem().getItemsInStock() + numberOfItems);
       saverToFiles.saveItems(items);
       
       
       
       System.out.println("--------------------");
    }


    private void addMoney() {
        System.out.println("---- Добавить деньги покупателю ----");
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()){
            System.out.println("Нет такого покупателя");
           return;
        }
        System.out.println("Выберите покупателя: ");
        int numberCustomer= insertInt(setNumbersCustomers);
        
        
        System.out.println("Введите сумму: ");
        double numberOfMoney= getDouble();
        
        Warehouse warehouse = new Warehouse();
        warehouse.setCustomer(customers.get(numberCustomer - 1));
        
        warehouse.getCustomer().setMoney(warehouse.getCustomer().getMoney() + numberOfMoney);
        saverToFiles.saveCustomers(customers);
        
        
        
        System.out.println("--------------------");
     }
    
    
    private Set<Integer> printListCustomers() {
        System.out.println("----- Список покупателей -----");
        Set<Integer> setNumbersCustomers = new HashSet<>();
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s %s. $ %s%n"
                       ,i+1
                       ,customers.get(i).getName()
                       ,customers.get(i).getLastname()
                       ,customers.get(i).getMoney()
                );
                setNumbersCustomers.add(i+1);
            }
        }
        return setNumbersCustomers;
    }
    
    
    private Set<Integer> printListItems() {
        System.out.println("----- Список продуктов -----");
        Set<Integer> setNumbersItems = new HashSet<>();
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i) != null){
                System.out.printf("%d. %s %s шт. $ %s%n"
                       ,i+1
                       ,items.get(i).getItemName()
                       ,items.get(i).getItemsInStock()
                       ,items.get(i).getItemPrice()
                );
                setNumbersItems.add(i+1);
            }
        }
        return setNumbersItems;
    }
    
    
    private void printTotalSold() {
        System.out.println("----- Всего продано на сумму " +warehouseHistory.get(1).getSoldTotal()+"$ -----");
        
    }

    private void sellItem() {
       System.out.println("---- Продажа продуктов ----");
       Set<Integer> setNumbersItems = printListItems();
       if(setNumbersItems.isEmpty()){
           System.out.println("Нет продуктов для продажы");
          return;
       }
       System.out.println("Выберите продукт: ");
       int numberItem= insertInt(setNumbersItems);
       
       
       System.out.println("Введите количество: ");
       int numberOfItems= getInt();
       
       
       
       Set<Integer> setNumbersCustomers = printListCustomers();
       System.out.println("Выберите покупателя: ");
       int numberCustomer= insertInt(setNumbersCustomers);
       Warehouse warehouse = new Warehouse();
       warehouse.setItem(items.get(numberItem - 1));
       warehouse.setCustomer(customers.get(numberCustomer - 1));
       
       double priceTotal = numberOfItems*warehouse.getItem().getItemPrice();
       
       
       
       warehouseHistory.add(warehouse);
       warehouse.getItem().setItemsInStock(warehouse.getItem().getItemsInStock() - numberOfItems);
       saverToFiles.saveItems(items);
       warehouse.getCustomer().setMoney(warehouse.getCustomer().getMoney() - priceTotal);
       saverToFiles.saveCustomers(customers);
       warehouse.setSoldTotal(warehouse.getSoldTotal()+ priceTotal);
       saverToFiles.saveWarehouseHistory(warehouseHistory);
       System.out.println("--------------------");
    }

}
