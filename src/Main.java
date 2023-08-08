import java.util.*;

public class Main {
    public static int getDistanceFromSource(String station,String [] stationListA,String [] stationListB){
        //binary search cannot be used here because the number of stations will be less than the kms always
        // using simple linear search

      for ( int i =0; i<stationListA.length; i+=2){
            String stn = stationListA[i];
            if(station.compareTo(stn)==0){
              return Integer.parseInt(stationListA[i+1]);
            }
      }
      for ( int i =0; i<stationListB.length; i+=2){
            String stn = stationListB[i];
            if(station.compareTo(stn)==0){
              return Integer.parseInt(stationListB[i+1]);
            }
      }
       return -1;
    }
    public static void main(String[] args) {
        String trainAStationString = "CHN 0 SLM 350 BLR 550 KRN 900 HYB 1200 NGP 1600 ITJ 1900 BPL 2000 AGA 2500 NDL 2700";
        String trainBStationString = "TVC 0 SRR 300 MAQ 600 MAO 1000 PNE 1400 HYB 2000 NGP 2400 ITJ 2700 BPL 2800 PTA 3800 NJP 4200 GHY 4700";

        String [] trainAStaionArr = trainAStationString.split(" ");
        String [] trainBStaionArr = trainBStationString.split(" ");

        List<TrainBogie> trainAStations = new LinkedList<>();
        List<TrainBogie> trainBStations = new LinkedList<>();

        //taking station order for train A
        for(int i =0 ; i<trainAStaionArr.length;i+=2){
            TrainBogie trainBogie = new TrainBogie();
            trainBogie.setStation(trainAStaionArr[i]);
            trainBogie.setDistance(Integer.parseInt(trainAStaionArr[i+1]));
            trainAStations.add(trainBogie);
        }

        //taking station order for train A
        for(int i =0 ; i<trainBStaionArr.length;i+=2){
            TrainBogie trainBogie = new TrainBogie();
            trainBogie.setStation(trainBStaionArr[i]);
            trainBogie.setDistance(Integer.parseInt(trainBStaionArr[i+1]));
            trainBStations.add(trainBogie);
        }



        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Train A and Train B Details : ");

        String[] trainAInput = scanner.nextLine().split(" ");
        String[] trainBInput = scanner.nextLine().split(" ");

        scanner.close();

//        System.out.println(Arrays.toString(trainAInput));
//        System.out.println(Arrays.toString(trainBInput));



        List<TrainBogie> trainABogies = new ArrayList<>();
        List<TrainBogie> trainAAtHyderabad = new ArrayList<>();
        List<TrainBogie> trainBAtHyderabad = new ArrayList<>();

        for (int i = 2; i < trainAInput.length; i++) {

            String station = trainAInput[i];
            int distance = getDistanceFromSource(station,trainAStaionArr,trainBStaionArr);

            if(distance>1200 )
                trainAAtHyderabad.add(new TrainBogie(station, distance));
        }

        for (int i = 2; i < trainBInput.length; i++) {
            String station = trainBInput[i];
            int distance = getDistanceFromSource(station,trainAStaionArr,trainBStaionArr);
            if(distance>2000 )
                trainBAtHyderabad.add(new TrainBogie(station, distance));
        }



        System.out.print("ARRIVAL TRAIN_A ENGINE ");
        for (TrainBogie bogie : trainAAtHyderabad) {
//            System.out.print(bogie.getStation() + " " + bogie.getDistance() +", ");
            System.out.print(bogie.getStation() + " ");
        }
        System.out.println();

        System.out.print("ARRIVAL TRAIN_B ENGINE ");
        for (TrainBogie bogie : trainBAtHyderabad) {
//            System.out.print(bogie.getStation() + " : " + bogie.getDistance() +", " );
            System.out.print(bogie.getStation() + " ");
        }
        System.out.println();
//
//        int trainALast = trainAAtHyderabad.size() > 0 ? trainAAtHyderabad.get(trainAAtHyderabad.size() - 1).distance : -1;
//        int trainBLast = trainBAtHyderabad.size() > 0 ? trainBAtHyderabad.get(trainBAtHyderabad.size() - 1).distance : -1;
//
//        if (trainALast == -1 && trainBLast == -1) {
//            System.out.println("DEPARTURE TRAIN_AB ENGINE ENGINE JOURNEY_ENDED");
//        } else if (trainALast == -1) {
//            System.out.print("DEPARTURE TRAIN_AB ENGINE ENGINE ");
//            for (TrainBogie bogie : trainBAtHyderabad) {
//                System.out.print(bogie.station + " " + bogie.distance + " ");
//            }
//            System.out.println(trainBEngine);
//        } else if (trainBLast == -1) {
//            System.out.print("DEPARTURE TRAIN_AB ENGINE ENGINE ");
//            for (TrainBogie bogie : trainAAtHyderabad) {
//                System.out.print(bogie.station + " " + bogie.distance + " ");
//            }
//            System.out.println(trainAEngine);
//        } else {
//            System.out.print("DEPARTURE TRAIN_AB ENGINE ENGINE ");
//            int indexA = 0, indexB = 0;
//            while (indexA < trainAAtHyderabad.size() && indexB < trainBAtHyderabad.size()) {
//                if (trainAAtHyderabad.get(indexA).distance >= trainBAtHyderabad.get(indexB).distance) {
//                    if (!trainAAtHyderabad.get(indexA).station.equals(trainBAtHyderabad.get(indexB).station)) {
//                        System.out.print(trainAAtHyderabad.get(indexA).station + " ");
//                    }
//                    indexA++;
//                } else {
//                    if (!trainAAtHyderabad.get(indexA).station.equals(trainBAtHyderabad.get(indexB).station)) {
//                        System.out.print(trainBAtHyderabad.get(indexB).station + " ");
//                    }
//                    indexB++;
//                }
//            }
//            while (indexA < trainAAtHyderabad.size()) {
//                if (!trainAAtHyderabad.get(indexA).station.equals(trainBAtHyderabad.get(indexB - 1).station)) {
//                    System.out.print(trainAAtHyderabad.get(indexA).station + " ");
//                }
//                indexA++;
//            }
//            while (indexB < trainBAtHyderabad.size()) {
//                if (!trainBAtHyderabad.get(indexB).station.equals(trainAAtHyderabad.get(indexA - 1).station)) {
//                    System.out.print(trainBAtHyderabad.get(indexB).station + " ");
//                }
//                indexB++;
//            }
//            System.out.println(trainAEngine + " " + trainBEngine);
//        }
    }
}
