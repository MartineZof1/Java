/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyClasses;

import Entity.Customer;
import Entity.Shoe;
import Entity.Store;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToFiles;

public class App {
    
    private Scanner scanner =new Scanner(System.in);
    private List<Shoe> shoes = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Store> storeHistory = new ArrayList<>();
    private SaverToFiles saverToFiles = new SaverToFiles();
    
    public App(){
        shoes = saverToFiles.loadShoes();
        customers = saverToFiles.loadCustomers();
        storeHistory = saverToFiles.loadStoreHistory();
    }
    
    public void run(){
        
        String repeat = "r";
        
        do{
            
            System.out.println("Выберите номер задачи:");
            System.out.println("1: Закончить программу");
            System.out.println("2: Добавить новую обувь"); 
            System.out.println("3: Добавить количество обуви этой модели");
            System.out.println("4: Список продаваемой обуви"); 
            System.out.println("5: Добавить покупателя"); 
            System.out.println("6: Список зарегистрированных покупателей"); 
            System.out.println("7: Покупка покупателем обуви"); 
            System.out.println("8: Доход магазина"); 
            System.out.println("9: Добавить денег покупателю"); 
            
            
            int task = getInt();
            
            
            switch (task){
                
                case 1:
                    repeat = "q";
                    System.out.println("До встречи!");
                    
                    break;
                
                case 2:
                    System.out.println("Добавить новую обувь: ");
                    addShoe();
                    break;
                        
                case 3:
                    System.out.println("Добавить количество обуви этой модели: ");
                    addShoesInStock();
                    break;
                             
                case 4:
                    System.out.println("Список продаваемой обуви: ");
                    printListShoes();
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
                    System.out.println("Покупка покупателем обуви: ");
                    sellShoe();
                    break;
                    
                case 8:
                    System.out.println("Доход магазина: ");
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
    
    
    private void addShoe() {
        Shoe shoe = new Shoe();
        System.out.print("Введите название обуви: ");
        shoe.setShoeName(scanner.nextLine());
        System.out.print("Введите цену обуви: ");
        shoe.setShoePrice(getDouble());
        System.out.print("Введите количество обуви: ");
        shoe.setShoesInStock(getInt());
        System.out.println("Обувь инициирована: "+shoe.toString());
        shoes.add(shoe);
        saverToFiles.saveShoes(shoes);
    }
    
    private void addShoesInStock() {
       System.out.println("---- Добавить количество продуктов ----");
       Set<Integer> setNumbersShoes = printListShoes();
       if(setNumbersShoes.isEmpty()){
           System.out.println("Нет такого продукта");
          return;
       }
       System.out.println("Выберите продукт: ");
       int numberShoe= insertInt(setNumbersShoes);
       
       
       System.out.println("Введите количество: ");
       int numberOfShoes= getInt();
       
       Store store = new Store();
       store.setShoe(shoes.get(numberShoe - 1));
       
       store.getShoe().setShoesInStock(store.getShoe().getShoesInStock() + numberOfShoes);
       saverToFiles.saveShoes(shoes);
       
       
       
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
        
        Store store = new Store();
        store.setCustomer(customers.get(numberCustomer - 1));
        
        store.getCustomer().setMoney(store.getCustomer().getMoney() + numberOfMoney);
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
    
    
    private Set<Integer> printListShoes() {
        System.out.println("----- Список продуктов -----");
        Set<Integer> setNumbersShoes = new HashSet<>();
        for (int i = 0; i < shoes.size(); i++) {
            if(shoes.get(i) != null){
                System.out.printf("%d. %s %s шт. $ %s%n"
                       ,i+1
                       ,shoes.get(i).getShoeName()
                       ,shoes.get(i).getShoesInStock()
                       ,shoes.get(i).getShoePrice()
                );
                setNumbersShoes.add(i+1);
            }
        }
        return setNumbersShoes;
    }
    
    
    private void printTotalSold() {
        System.out.println("----- Всего продано на сумму " +storeHistory.get(1).getSoldTotal()+"$ -----");
        
    }

    private void sellShoe() {
       System.out.println("---- Продажа продуктов ----");
       Set<Integer> setNumbersShoes = printListShoes();
       if(setNumbersShoes.isEmpty()){
           System.out.println("Нет продуктов для продажы");
          return;
       }
       System.out.println("Выберите продукт: ");
       int numberShoe= insertInt(setNumbersShoes);
       
       
       System.out.println("Введите количество: ");
       int numberOfShoes= getInt();
       
       
       
       Set<Integer> setNumbersCustomers = printListCustomers();
       System.out.println("Выберите покупателя: ");
       int numberCustomer= insertInt(setNumbersCustomers);
       Store store = new Store();
       store.setShoe(shoes.get(numberShoe - 1));
       store.setCustomer(customers.get(numberCustomer - 1));
       
       double priceTotal = numberOfShoes*store.getShoe().getShoePrice();
       
       
       
       storeHistory.add(store);
       store.getShoe().setShoesInStock(store.getShoe().getShoesInStock() - numberOfShoes);
       saverToFiles.saveShoes(shoes);
       store.getCustomer().setMoney(store.getCustomer().getMoney() - priceTotal);
       saverToFiles.saveCustomers(customers);
       store.setSoldTotal(store.getSoldTotal()+ priceTotal);
       saverToFiles.saveStoreHistory(storeHistory);
       System.out.println("--------------------");
    }

}