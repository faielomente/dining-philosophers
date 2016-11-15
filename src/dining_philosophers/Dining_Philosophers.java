/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dining_philosophers;

import java.util.Scanner;

/**
 *
 * @author faielomente
 */
public class Dining_Philosophers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        int no_philosophers;
        int hungry;
        
        System.out.println("Number of PHILOSOPHERS: ");
        no_philosophers = scanner.nextInt();
        System.out.println("How many of them are hungry?");
        hungry = scanner.nextInt();
        
            
    }
    
}
