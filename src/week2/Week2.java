 
package week2;
import java.util.Scanner;


public class Week2 {
 
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int currentPassenger = 0;
        
        System.out.println("Enter the seating capacity: ");
        int seatingCapacity = scanner.nextInt();
        
        System.out.println("How many stops are on the route?: ");
        int stopNumber = scanner.nextInt();
        scanner.nextLine();
        
        String[] stopNames = new String[stopNumber];
        int[] boarding = new int[stopNumber];
        int[] alighting = new int[stopNumber];
        
        int[] occupancy = new int[stopNumber];
        int overCount = 0;
        boolean[] dataError = new boolean[stopNumber];
        
        for(int i = 0; i < stopNumber; i++){
            System.out.println("Enter the "+(i+1)+". stop name: ");
            stopNames[i] = scanner.nextLine();           
            
            System.out.println("Enter passengers boarding: ");    
            boarding[i] = scanner.nextInt();
            
            System.out.println("Enter passengers alighting: ");   
            alighting[i] = scanner.nextInt();
            scanner.nextLine();
        }
        
        for(int i = 0; i < stopNumber; i++){
            System.out.println("===== "+(i+1)+". STOP ===== ");
            System.out.println("Name: "+stopNames[i]);
            System.out.println("Boarding: "+boarding[i]);
            System.out.println("Alighting: "+alighting[i]);
            
            currentPassenger = currentPassenger + boarding[i];
            if(alighting[i] > currentPassenger){
                dataError[i] = true;
                currentPassenger = 0;
            } else {
                currentPassenger = currentPassenger - alighting[i];
            }
            occupancy[i] = currentPassenger;
        }
        
        System.out.println("===== WARNINGS ===== ");
        for(int i = 0; i < stopNumber; i++){
            System.out.println("["+stopNames[i]+"] After this stop, the number of current passengers: "+occupancy[i]);
            System.out.println("Boarding: "+boarding[i]);
            System.out.println("Alighting: "+alighting[i]);
            
            if(dataError[i]){
                System.out.println("Data error at "+stopNames[i]+": cannot have more passengers alighting than are currently on the bus. Occupancy set to 0.");
            }
            if(occupancy[i] > seatingCapacity){
                System.out.println("Warning: Bus is over capacity at "+stopNames[i]+"!");
                overCount++;
            }
        }
        
        System.out.println("================================================================");
        System.out.printf("%-15s %-10s %-10s %-10s%n", "STOP NAME", "BOARDING", "ALIGHTING", "OCCUPANCY");
        System.out.println("----------------------------------------------------------------");
        for(int i = 0; i < stopNumber; i++){
            System.out.printf("%-15s %-10d %-10d %-10d%n", stopNames[i], boarding[i], alighting[i], occupancy[i]);
        }
        
        System.out.println("-------STATISTICS-------");
        int maxIndex = 0;
        int total = 0;
        for(int i = 0; i < stopNumber; i++){
            if(boarding[i] > boarding[maxIndex]){
                maxIndex = i;
            }
            total = total + occupancy[i];
        }
        double average = (double) total / stopNumber;
        
        System.out.println("Busiest stop: "+stopNames[maxIndex]+" ("+boarding[maxIndex]+" passengers boarding)");
        System.out.println("Average occupancy: "+average);
        System.out.println("The number of stops that exceeded the bus capacity: "+overCount);
        
        if(currentPassenger != 0){
            System.out.println("Warning: "+currentPassenger+" passengers still on the bus after the final stop -- please check your data.");
        }
    }
    
}
