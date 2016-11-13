/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dining_philosophers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
        Dining_Philosophers dp =  new Dining_Philosophers();
        
        Scanner scanner = new Scanner(System.in);
        int no_philosophers, no_hungry;

        System.out.println("How many PHILOSOPHERS are there?");
        no_philosophers = scanner.nextInt();
        System.out.println("Among them how many are HUNGRY?");
        no_hungry = scanner.nextInt();
        
        int [] philosophers = new int [no_philosophers];
        int [] hungry = new int[no_hungry];

        //Populate the philosophers array
        for (int i = 0; i < no_philosophers; i++){
            philosophers[i] = i;
        }
        
        HashMap<Integer, Philosopher> occupiedSeats = new HashMap();

        System.out.println("Who are/is HUNGRY?");
        for (int i = 0; i < no_hungry; i++) {
            System.out.println("PHILOSOPHER ID: ");
            int tmp_id = scanner.nextInt();

            System.out.println("Seat Number: ");
            int tmp_seatNo = scanner.nextInt();

            occupiedSeats.put(tmp_seatNo, new Philosopher(tmp_id));
        }
        
        HashMap<Integer, Integer> diningTable = dp.setTable (philosophers, occupiedSeats);
        dp.permute(occupiedSeats.keySet().toArray());
        
    }
    
    public void dineIn(HashMap<Integer, Philosopher> seats, int[] hungry){
        
    }
    
    public void permute(Object[] o) {
        int n = o.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = (int)o[i];
        permute(a, n);
    }
    
    // print n! permutation of the elements of array a (not in order)
    private void permute(int[] a, int n) {
        if (n == 1) {
            System.out.println(java.util.Arrays.toString(a));
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            permute(a, n-1);
            swap(a, i, n-1);
        }
    } 
    
    private void swap(int[] a, int i, int j) {
        int c = a[i];
        a[i] = a[j];
        a[j] = c;
    }

    public HashMap<Integer, Integer> setTable(int[] philosophers, HashMap occupiedSeats){
        Object[] keys = occupiedSeats.keySet().toArray();
        HashMap<Integer, Integer> seats = new HashMap();

        keys = bubbleSort(keys);

        for (int i = 0; i < philosophers.length; i++) {
            seats.put(i, -1);

            if (occupiedSeats.containsKey(i))
                seats.put(i, (int)occupiedSeats.get(i));
        }
        
        Collection p = seats.values();
        
        for(Map.Entry entry : seats.entrySet()){
            if((Integer)entry.getValue() == -1){
                for(int i = 0; i < philosophers.length; i++){
                    if(p.contains(i) == false){
                        seats.replace((Integer)entry.getKey(), (Integer)entry.getValue(), i);
                        p = seats.values();
                        break;
                    }
                }
            }     
        }
        
        //Print the seat order
        for(Map.Entry entry : seats.entrySet()){
            System.out.println("Seat No." + entry.getKey().toString() + " -> Philosopher " + entry.getValue().toString());
        }

        return seats;
    }

    private Object [] bubbleSort(Object [] array){

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if ((int)array[j] > (int)array[j + 1]){
                    int tmp = (int)array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }

        return array;
    }
    
}
