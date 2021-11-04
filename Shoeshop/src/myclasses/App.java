/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.brand;
import entity.shoes;
import entity.history;
import entity.customers;
import interfaces.Keeping;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author user
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    //---------- Данные библиотеки ----------
    private List<shoes> books = new ArrayList<>();
    private List<customers> readers = new ArrayList<>();
    private List<history> histories = new ArrayList<>();
    // -------- сохранение --------
    private Keeping keeping = new KeeperToFile();
    
    public App(){
        books = keeping.loadShoes();
        readers = keeping.loadCustomers();
        histories = keeping.loadHistories();
    }
    
    public void run(){
       String repeat = "r";
        do{
            System.out.println("Выберите номер задачи:");
            System.out.println("0: Закончить программу");
            System.out.println("1: Добавить модель");
            System.out.println("2: Список продаваемых моделей");
            System.out.println("3: Добавить покупателя");
            System.out.println("4: Список зарегистрированных покупателей");
            System.out.println("5: Покупка покупателем обуви");
            System.out.println("6: Доход магазина за все время работы");
            System.out.println("7: Добавить денег покупателю");
            int task = scanner.nextInt(); scanner.nextLine();
            switch (task) {
                case 0:
                    repeat="q";
                    System.out.println("Пока!");
                    break;
                case 1:
                    addShoes();
                    break;
                case 2:
                    printListShoes();
                    break;
                case 3:
                    addCustomers();
                    break;
                case 4:
                    printListCustomers();
                    break;
                case 5:
                    addHistory();
                    break;
                case 6:
                    returnShoes();
                    break;
                case 7:
                    printListGivenShoes();
                    break;
                default:
                    System.out.println("Выберите цифру из списка!");;
            }
        }while("r".equals(repeat));
    }
    private void addShoes(){
        System.out.println("--- Добавление модели ---");
        shoes book = new shoes();
        System.out.print("Введите название модели: ");
        book.setShoesName(scanner.nextLine());
        System.out.print("Введите brand: ");
        book.setBrand(scanner.nextLine());
        System.out.print("Введите количество этой модели: ");
        book.setQuantity(scanner.nextInt());scanner.nextLine();
        book.setPrice(book.getQuantity());
        System.out.print("Введите цену: ");
        book.setPrice(scanner.nextInt());scanner.nextLine();
        System.out.println("---------------------");
        books.add(book);
        keeping.saveShoes(shoes);
    }
    private void addCustomers(){
        System.out.println("--- Добавление покупателя ---");
        customers reader = new customers();
        System.out.println("Имя покупателя");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Фамилия покупател");
        reader.setLastname(scanner.nextLine());
        System.out.println("Телефон покупател");
        reader.setPhone(scanner.nextLine());
        System.out.println("---------------------");
        readers.add(reader);
        keeping.saveCustomers(readers);
    }

    private void addHistory() {
        System.out.println("--- Продажа обуви ---");
        System.out.println("Список обуви:");
        history history = new history();
        int n = 0;
        for (int i = 0; i < shoes.size(); i++) {
            if(shoes.get(i) != null && shoes.get(i).getPrice()>0){
                StringBuilder sbBrandName = new StringBuilder();
                for (int j = 0; j < shoes.get(i).getBrand().length; j++) {
                    sbBrandName.append(shoes.get(i).getBrand()[j].getFirstname())
                                    .append(" ")
                                    .append(shoes.get(i).getBrand()[j].getLastname())
                                    .append(". ");
                }
                
                System.out.println(i+1
                        +". "+shoes.get(i).getShoesName()
                        +". "+sbBrandName.toString()
                        +". В наличии: " + shoes.get(i).getPrice()
                );
                n++;
            }
        }
        if(n < 1){
            System.out.println("Нет модели");
            return;
        }
        System.out.print("Выберите номер модели: ");
        int numberShoes = scanner.nextInt(); scanner.nextLine();
        System.out.println("Список покупателей:");
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.println(i+1+". "+customers.get(i).toString());
            }
        }
        System.out.print("Выберите номер покупателя: ");
        int numberCustomers = scanner.nextInt(); scanner.nextLine();
        history.setShoes(books.get(numberShoes-1));
        history.setCustomers(readers.get(numberCustomers-1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        history.getShoes().setPrice(history.getShoes().getPrice() - 1);
        keeping.saveShoes(books);
        histories.add(history);
        keeping.saveHistories(histories);
        System.out.println("Модель "+history.getShoes().getShoesName()
                            +" продана "+history.getCustomers().getFirstname()
                            +" " +history.getCustomers().getLastname()
        );
        System.out.println("-------------------");
        
    }

    private void printListShoes() {
        System.out.println("--- Список книг ---");
        for (int i = 0; i < shoes.size(); i++) {
            if(books.get(i) != null && books.get(i).getPrice() > 0){
                System.out.printf("%d. %s. %s. %d. В наличии екземпляров: %d%n"
                        ,i+1
                        ,books.get(i).getShoesName()
                        ,books.get(i).getPrice()
                );
            }else if(shoes.get(i) != null){
                System.out.printf("%d. %s. %s. %d. Книга читается до: %s%n"
                        ,i+1
                        ,books.get(i).getShoesName()
                        ,customers.get(i).getCustomers()
                        ,Arrays.toString(shoes.get(i).getCustomers())
                        ,getReturnDate(shoes.get(i))
                );
            }
        }
        System.out.println("-------------------");
    }
    private String getReturnDate(shoes shoes){
        for (int i = 0; i < histories.size(); i++) {
            if(shoes.getShoesName().equals(histories.get(i).getShoes().getShoesName())
                    && histories.get(i).getReturnedDate() == null){
                Date givenDate = histories.get(i).getGivenDate();
                LocalDate localGivenDate = givenDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localGivenDate = localGivenDate.plusDays(14);
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        }
        return "";
    }
    private void printListReaders() {
        System.out.println("--- Список покупателей ---");
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.println(customers.get(i).toString());
            }
        }
        System.out.println("-------------------");
    }

    private void printListGivenShoes() {
        System.out.println("Список проданой обуви:");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null && histories.get(i).getReturnedDate() == null){
                System.out.println(i+1+". Обувь "
                        +histories.get(i).getShoes().getShoesName()
                        +" продана "+histories.get(i).getCustomers().getFirstname()
                        +" "+histories.get(i).getCustomers().getLastname()
                );
                n++;
            }
        }
        if(n < 1){
            System.out.println("Нет проданной обуви!");
            System.out.println("-------------------");
        }
    }
   
}