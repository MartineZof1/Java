/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sktvp20task3pavlenko;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class SKTVp20Task3Pavlenko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Ваше Имя : ");
        String name = input.nextLine();

        System.out.print("Ваша Фамилия: ");
        String secondName = input.nextLine();

        System.out.print("Ваша Год рождения: ");
        int year = input.nextInt();
        
        input.nextLine();
        System.out.print("Ваша Месяц рождения: ");
        String month = input.nextLine();
        
        System.out.print("Ваша День рождения: ");
        int day = input.nextInt();
           
        System.out.println(name + " " + secondName + " " + "родился" + " " + day + " " + month + " " + year + "года.");
    }
}
