/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sktvp20arrayspro;

import java.util.Arrays;

/**
 *
 * @author user
 */
public class SKTVp20ArraysPro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] nums;
        nums = new int[7];
        nums[0]=1;
        nums[1]=2;
        nums[2]=3;
        nums[3]=4;
        nums[4]=5;
        int[] nums2 = nums;
        System.out.print("nums = ");
        System.out.print("[");
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i]);
            System.out.print(", ");
        }
        System.out.println("]");
        System.out.print("nums2 = ");
        System.out.print("[");
        for(int i = 0; i < nums2.length; i++){
            System.out.print(nums2[i]);
            System.out.print(", ");
        }
        System.out.println("]");
        
    }
    
}
