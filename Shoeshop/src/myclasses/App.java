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
        System.out.println("--- Добавление читателя ---");
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
                        +". "+books.get(i).getShoesName()
                        +". "+sbBrandName.toString()
                        +". В наличии: " + books.get(i).getPrice()
                );
                n++;
            }
        }
        if(n < 1){
            System.out.println("Нет книг для чтения");
            return;
        }
        System.out.print("Выберите номер книги: ");
        int numberBook = scanner.nextInt(); scanner.nextLine();
        System.out.println("Список читателей:");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.println(i+1+". "+readers.get(i).toString());
            }
        }
        System.out.print("Выберите номер читателя: ");
        int numberReader = scanner.nextInt(); scanner.nextLine();
        history.setBook(books.get(numberBook-1));
        history.setReader(readers.get(numberReader-1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        history.getBook().setCount(history.getBook().getCount() - 1);
        keeping.saveBooks(books);
        histories.add(history);
        keeping.saveHistories(histories);
        System.out.println("Книга "+history.getBook().getBookName()
                            +" выдана читателю "+history.getReader().getFirstname()
                            +" " +history.getReader().getLastname()
        );
        System.out.println("-------------------");
        
    }

    private void printListBooks() {
        System.out.println("--- Список книг ---");
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.printf("%d. %s. %s. %d. В наличии екземпляров: %d%n"
                        ,i+1
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors())
                        ,books.get(i).getPublishedYear()
                        ,books.get(i).getCount()
                );
            }else if(books.get(i) != null){
                System.out.printf("%d. %s. %s. %d. Книга читается до: %s%n"
                        ,i+1
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors())
                        ,books.get(i).getPublishedYear()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        System.out.println("-------------------");
    }
    private String getReturnDate(Book book){
        for (int i = 0; i < histories.size(); i++) {
            if(book.getBookName().equals(histories.get(i).getBook().getBookName())
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
        System.out.println("--- Список читателей ---");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.println(readers.get(i).toString());
            }
        }
        System.out.println("-------------------");
    }

    private void returnBook() {
        System.out.println("--- Возврат книги ---");
        System.out.println("Список читаемых книг:");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null
                 && histories.get(i).getReturnedDate() == null
                    && histories.get(i).getBook().getCount() 
                    <  histories.get(i).getBook().getQuantity()
            ){
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                        ,i+1
                        ,histories.get(i).getBook().getBookName()
                        ,histories.get(i).getReader().getFirstname()
                        ,histories.get(i).getReader().getLastname()
                );
                 n++;
            }
        }
        if(n < 1){
            System.out.println("Нет читаемых книг!");
            System.out.println("-------------------");
            return;
        }
        System.out.print("Выберите номер возврщаемой книги: ");
        int numberHistory = scanner.nextInt(); scanner.nextLine();
        Calendar c = new GregorianCalendar();
        histories.get(numberHistory - 1).setReturnedDate(c.getTime());
        histories.get(numberHistory - 1).getBook().setCount(
                histories.get(numberHistory - 1).getBook().getCount()+1
        );
        keeping.saveBooks(books);
        keeping.saveHistories(histories);
        System.out.println("Книга "
                +histories.get(numberHistory - 1).getBook().getBookName()
                +" возвращена в библиотеку"
        );
        System.out.println("-------------------");
    }

    private void printListGivenBooks() {
        System.out.println("Список читаемых книг:");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null && histories.get(i).getReturnedDate() == null){
                System.out.println(i+1+". Книгу "
                        +histories.get(i).getBook().getBookName()
                        +" читает "+histories.get(i).getReader().getFirstname()
                        +" "+histories.get(i).getReader().getLastname()
                );
                n++;
            }
        }
        if(n < 1){
            System.out.println("Нет читаемых книг!");
            System.out.println("-------------------");
        }
    }
   
}