/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sktvp20task2pavlenko;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class SKTVp20Task2Pavlenko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите число : ");
        int number = input.nextInt();
        int sum = 0;
        int cifra;
        cifra = number % 10;
        sum = sum + cifra;        
        number = number / 10;      
        cifra = number % 10;
        sum = sum + cifra;
        number = number / 10;
        sum = sum + number;
        
        System.out.println(sum);
    }
    
}
