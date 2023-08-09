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

        List<TrainBogie> trainABBogies = new ArrayList<>();
        List<TrainBogie> trainAAtHyderabad = new ArrayList<>();
        List<TrainBogie> trainBAtHyderabad = new ArrayList<>();

        for (int i = 2; i < trainAInput.length; i++) {

            String station = trainAInput[i];
            int distance = getDistanceFromSource(station,trainAStaionArr,trainBStaionArr);

            if(distance>=1200 )
                trainAAtHyderabad.add(new TrainBogie(station, distance));
        }

        for (int i = 2; i < trainBInput.length; i++) {
            String station = trainBInput[i];
            int distance = getDistanceFromSource(station,trainAStaionArr,trainBStaionArr);
            if(distance>=2000 )
                trainBAtHyderabad.add(new TrainBogie(station, distance));
        }

        System.out.print("ARRIVAL TRAIN_A ENGINE ");
        for (TrainBogie bogie : trainAAtHyderabad) {
            System.out.print(bogie.getStation() + " ");
        }
        System.out.println();

        System.out.print("ARRIVAL TRAIN_B ENGINE ");
        for (TrainBogie bogie : trainBAtHyderabad) {
            System.out.print(bogie.getStation() + " ");
        }
        System.out.println();


        Collections.sort(trainAAtHyderabad);
        Collections.sort(trainBAtHyderabad);
        Collections.sort(trainABBogies);


        System.out.print("DEPARTURE TRAIN_AB ");
        if(!trainAAtHyderabad.isEmpty()){
            System.out.print("ENGINE ");
        }if(!trainBAtHyderabad.isEmpty()){
            System.out.print("ENGINE ");
        }
        for (TrainBogie bogie : trainABBogies) {
            System.out.print(bogie.getStation() + " ");
        }

    }
}
