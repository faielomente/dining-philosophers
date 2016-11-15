/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dining_philosophers;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
        int choice = 0;
        
        Scanner scanner = new Scanner(System.in);
        int no_philosophers, no_hungry;

        System.out.println("How many PHILOSOPHERS are there?");
        no_philosophers = scanner.nextInt();
        System.out.println("Among them how many are HUNGRY?");
        no_hungry = scanner.nextInt();
        
        int [] philosophers = new int [no_philosophers];
//        int [] hungry = new int[no_hungry];
        
        

        //Populate the philosophers array
        for (int i = 0; i < no_philosophers; i++){
            philosophers[i] = i;
        }
        
        HashMap<Integer, Integer> occupiedSeats = new HashMap();

        System.out.println("Who are/is HUNGRY?");
        for (int i = 0; i < no_hungry; i++) {
            System.out.println("PHILOSOPHER ID: ");
            int tmp_id = scanner.nextInt();

            System.out.println("Seat Number: ");
            int tmp_seatNo = scanner.nextInt();

            occupiedSeats.put(tmp_seatNo, tmp_id);
            
        }
//        if(dp.hasConsecutive(occupiedSeats.keySet().toArray()) == true){
//            System.out.println("INVALID! DEADLOCK IS POSSIBLE");
//        }
        
        System.out.println("Choose among the available SCENARIOS:\n1. ONE can EAT at a time.\n2. TWO an EAT at a time.");
        choice = scanner.nextInt();
        
        HashMap<Integer, Integer> diningTable = dp.setTable (philosophers, occupiedSeats);
        HashMap<Integer, Philosopher> arrangement = dp.dineIn(diningTable);
        
        if(choice == 1){
            dp.single(arrangement, occupiedSeats.keySet());
        }
        else if(choice == 2){
            
            dp.dou(arrangement, occupiedSeats.keySet());
        }
        
    }
    
    //Assigns seat to a philosopher
    private HashMap<Integer, Philosopher> dineIn (HashMap<Integer, Integer> diningTable){
        HashMap<Integer, Philosopher> tableArrangement = new HashMap<>();
        
        for(Map.Entry seat : diningTable.entrySet()){
            tableArrangement.put((Integer)seat.getKey(), new Philosopher((int)seat.getValue()));
        }
        
        for(Map.Entry seat : tableArrangement.entrySet()){
            Philosopher p = (Philosopher) seat.getValue();
            System.out.println("Seat No." + seat.getKey() + " -> " + "Philo " + p.getId());
        }
        
        return tableArrangement;
    }
    
    public void single(HashMap<Integer, Philosopher> seats, Set hungry){
        
        while(hungry.isEmpty() == false){
            int count = 0;
            System.out.println("--------------------------------------------------------------");
            for(Map.Entry seat : seats.entrySet()){
                Philosopher p = (Philosopher) seat.getValue();

                if(hungry.contains(seat.getKey()) && count == 0){
                    p.setIsEating(true);
                    System.out.println("Philosopher " + p.getId() + " is EATING.");
                    p.setDone(true);
                    seats.replace((Integer)seat.getKey(), (Philosopher) seat.getValue(), p);
                    hungry.remove(seat.getKey());
                    count ++;
                }
                else
                    System.out.println("Philosopher " + p.getId() + " is THINKING.");
            }
        }
    }
    
    public void dou(HashMap<Integer, Philosopher> seats, Set hungry){
        TreeSet<Integer> sorted = new TreeSet<>(hungry);
        
        while(hungry.isEmpty() == false){
//            System.out.println("item: " + item);
            int count = 0;
            System.out.println("--------------------------------------------------------------");
            for(Map.Entry seat : seats.entrySet()){
                Philosopher p = (Philosopher) seat.getValue();
//                System.out.println(p.getId());
                
                if(sorted.contains(seat.getKey()) && p.isIsEating() == false){
                    Integer item = (Integer) seat.getKey();
                    boolean available = areForksAvailable(seats, sorted, item);
//                    boolean beside = seats.get((item+1)%(seats.size()-1)).isIsEating();
                    
                    if (available == true && count < 2){
                        p.setIsEating(true);
                        System.out.println("Philosopher " + p.getId() + " is EATING.");
                        p.setDone(true);
                        seats.replace((Integer)seat.getKey(), (Philosopher) seat.getValue(), p);
                        hungry.remove(item);
                        count++;
                    }
                    else
                        System.out.println("Philosopher " + p.getId() + " is THINKING.");
                }
                else
                    System.out.println("Philosopher " + p.getId() + " is THINKING.");
            }
        }
        
    }
    
    private boolean areForksAvailable(HashMap<Integer, Philosopher> seats, TreeSet<Integer> ts, Integer item){
        Integer prev = ts.lower(item);
        Integer next = ts.higher(item);
        
        if(prev == null)
            prev = ts.last();
        else if (next == null)
            next = ts.first();
        
        if(((item-prev) == 1) || ((next - item) == 1) || (next == 0) || (prev == (seats.size()-1))){
            if((seats.get(prev).isIsEating() == false && seats.get(next).isIsEating() == true) || 
                    (seats.get(prev).isIsEating() == true && seats.get(next).isIsEating() == false)){
                return false;
            }
        }
        
        return true;
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
