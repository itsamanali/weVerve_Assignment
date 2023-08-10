import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Station list with distance from source
        String trainAStationString = "CHN 0 SLM 350 BLR 550 KRN 900 HYB 1200 NGP 1600 ITJ 1900 BPL 2000 AGA 2500 NDL 2700";
        String trainBStationString = "TVC 0 SRR 300 MAQ 600 MAO 1000 PNE 1400 HYB 2000 NGP 2400 ITJ 2700 BPL 2800 PTA 3800 NJP 4200 GHY 4700";

        //creating new object of type train
        // train is a LinkedList type data structure which have engine and bogies attached to it
        Train A = new Train(trainAStationString,"TRAIN_A");
        Train B = new Train(trainBStationString,"TRAIN_B");

        //taking inputs from user
        Scanner scanner = new Scanner(System.in);
        // this line is optional
        System.out.println("Enter Train A and Train B Details : ");
        // also spliting the input string and storing in array
        String[] trainAInput = scanner.nextLine().split(" ");
        String[] trainBInput = scanner.nextLine().split(" ");
        scanner.close();

        // adding new Bogie in order of input
        for (int i = 2 ; i< trainAInput.length ; i++){ A.add(trainAInput[i]); }
        for (int i = 2 ; i< trainBInput.length ; i++){ B.add(trainBInput[i]); }

        // this function is required to insert destination distance which are not on train A station list
        A.fullFillDistances(B.distanceMap);
        B.fullFillDistances(A.distanceMap);

        // As it is mention in assignment that we need to remove all
        // the bogies which have destination distance less than HYB
        A.removeBogiesWithDistanceLessThanHYB();
        if(A.size>0) {
            System.out.print("ARRIVAL TRAIN_A ");
            System.out.print("ENGINE ");
            // print function prints recursively
            // passing head.next because the head is just a dummy node
            A.print(A.head.next);
            System.out.println();
        }

        B.removeBogiesWithDistanceLessThanHYB();
        if(B.size>0) {
            System.out.print("ARRIVAL TRAIN_B ");
            System.out.print("ENGINE ");
            B.print(B.head.next);
            System.out.println();
        }

        // while departing from HYB we need to detach the HYB bogie
        // I can remove HYB bogie after merging also but i am doing here
        A.removeHYB(A.head.next);
        B.removeHYB(B.head.next);

        // if no bogie left while departing from HYB
        if(A.size + B.size == 0 )
            System.out.println("JOURNEY_ENDED");
        else {
            // I am sorting train based on destination distance
            // because i want to merge as a two sorted lists
            A.sortList();
            B.sortList();

            int mergedSize = A.size + B.size;
            // this new train have access to both train's station
            Train AB = new Train(trainAStationString, trainBStationString, mergedSize);

            // this is the merging function
            AB.head = AB.mergeTwoLists(B.head.next, A.head.next);

            // this function groups the bogies having same destination
            AB.head = AB.rearrange();

            //this function reverse the linkedlist as they are sorted in accending order but we want descending order
            AB.head = AB.reverse(AB.head);

            System.out.print("DEPARTURE TRAIN_AB ");
            if (A.size > 0) System.out.print("ENGINE ");
            if (B.size > 0) System.out.print("ENGINE ");
            AB.print(AB.head);
        }
        System.gc();
    }

}
