/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sktvp20task4pavlenko;

import java.util.Random;        


/**
 *
 * @author user
 */
public class SKTVp20Task4Pavlenko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int[] array = new int[100];
       for (int i = 0; i < array.length; i++) {
           array[i] = (int) (Math.random() * 10);
       }
       for (int i = 0; i < array.length; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
               
    }
}
