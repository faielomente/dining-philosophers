/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dining_philosophers;

import java.util.HashMap;
import java.util.Objects;
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
        int no_philosphers, no_hungry;

        System.out.println("How many PHILOSOPHERS are there?");
        no_philosphers = scanner.nextInt();
        System.out.println("Among them how many are HUNGRY?");
        no_hungry = scanner.nextInt();

//        int [] philo_id = new int[no_hungry];
//        int [] seat_no = new int[no_hungry];

        HashMap<Integer, Integer> seats = new HashMap();

        System.out.println("Who are/is HUNGRY?");
        for (int i = 0; i < no_philosphers; i++) {
            System.out.println("PHILOSOPHER ID: ");
            int tmp_id = scanner.nextInt();

            System.out.println("Seat Number: ");
            int tmp_seatNo = scanner.nextInt();

            seats.put(tmp_seatNo, tmp_id);
        }
    }

//    public HashMap<Integer, Integer> setTable(int no_philo, HashMap<Integer, Integer> occupiedSeats){
//        Object[] keys = occupiedSeats.keySet().toArray();
//        HashMap<Integer, Integer> seats = new HashMap();
//
//        keys = bubbleSort(keys);
//
//        for (int i = 0; i < no_philo; i++) {
//            seats.put(i, -1);
//
//            if (occupiedSeats.containsKey(i))
//                seats.put(i, occupiedSeats.get(i));
//        }
//
//        return seats;
//    }

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
