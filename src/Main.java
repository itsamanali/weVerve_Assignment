import java.util.*;

public class Main {
    public static void main(String[] args) {
        String trainAStationString = "CHN 0 SLM 350 BLR 550 KRN 900 HYB 1200 NGP 1600 ITJ 1900 BPL 2000 AGA 2500 NDL 2700";
        String trainBStationString = "TVC 0 SRR 300 MAQ 600 MAO 1000 PNE 1400 HYB 2000 NGP 2400 ITJ 2700 BPL 2800 PTA 3800 NJP 4200 GHY 4700";

        Train A = new Train(trainAStationString,"TRAIN_A");
        Train B = new Train(trainBStationString,"TRAIN_B");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Train A and Train B Details : ");
        String[] trainAInput = scanner.nextLine().split(" ");
        String[] trainBInput = scanner.nextLine().split(" ");
        scanner.close();

        for (int i = 2 ; i< trainAInput.length ; i++){ A.add(trainAInput[i]); }
        for (int i = 2 ; i< trainBInput.length ; i++){ B.add(trainBInput[i]); }

        A.fullFillDistances(B.distanceMap);
        B.fullFillDistances(A.distanceMap);

        A.removeBogiesWithDistanceLessThanHYB();
        if(A.size>0) {
            System.out.print("TRAIN_A ARRIVAL ");
            System.out.print("ENGINE ");
            A.print(A.head.next);
            System.out.println();
        }

        B.removeBogiesWithDistanceLessThanHYB();
        if(B.size>0) {
            System.out.print("TRAIN_B ARRIVAL ");
            System.out.print("ENGINE ");
            B.print(B.head.next);
            System.out.println();
        }

        A.removeHYB(A.head.next);
        B.removeHYB(B.head.next);

        if(A.size + B.size == 0 ) System.out.println("JOURNEY_ENDED");
        else {
            A.sortList();
            B.sortList();

            int mergedSize = A.size + B.size;
            Train AB = new Train(trainAStationString, trainBStationString, mergedSize);
            AB.head = AB.mergeTwoLists(B.head.next, A.head.next);

            AB.head = AB.rearrange();
            AB.head = AB.reverse(AB.head);

            System.out.print("DEPARTURE TRAIN_AB ");
            if (A.size > 0) System.out.print("ENGINE ");
            if (B.size > 0) System.out.print("ENGINE ");
            AB.print(AB.head);
        }
        System.gc();
    }

}
