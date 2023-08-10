
import java.util.*;

public class Train {
    String name;
    Bogie engine;
    Bogie head;
    String [] stationList;
    Map<String,Integer> distanceMap;
    int size;
    public Train() {
    }
    public Train(String stationA,String stationB,int size) {
        this.size = size;
        String att = stationA+stationB;

    }

    public Train(String stationDistance,String name) {
        this.name = name;
        this.size = 0;
        stationList = stationDistance.split(" ");

        engine = new Bogie();
        engine.dist = Integer.parseInt(stationList[stationList.length-1]);
        engine.isEngine =true;
        engine.srcStn = stationList[0];

        head = new Bogie();
        head.srcStn = stationList[0];

        head.next = this.engine;
        distanceMap = new HashMap<>();
        for(int i =0 ; i<stationList.length; i+=2){
            distanceMap.put(stationList[i],Integer.parseInt(stationList[i+1]));
        }
    }
    public boolean kyaEngHai(){
        Bogie temp = head;
        while (temp != null){
            if(temp.isEngine) return temp.isEngine;
            else temp =temp.next;
        }
        return false;
    }
    public void add(String destination){
        Bogie bogie = new Bogie();
        size++;
        bogie.srcStn = stationList[0];
        bogie.destStn = destination;
        bogie.dist = findDistance(destination);

        bogie.next = head.next;
        head.next = bogie;
    }
    public void removeHYB(Bogie head){

        Bogie temp = head;
        while (temp.next != null ){
            if(temp.next.destStn != null && temp.next.destStn.equals("HYB")){
                temp.next=temp.next.next;
            }
            temp=temp.next;
        }
    }
    public void removeBogiesWithDistanceLessThanHYB(){
        int HYBDistance = findDistance("HYB");

        Bogie slow = head;
        Bogie fast = head.next;
        Bogie temp;
        //while fast pointer does not reached the engine
        while (!fast.isEngine ){
            //if distance is less then remove
            if(fast.dist > 0 && fast.dist < HYBDistance) {
                temp = fast.next;
                slow.next = temp;
                fast.next = null;
                fast = temp;
                size--;
            }else {
                slow = slow.next;
                fast = fast.next;
            }
        }


    }
    public void sortList() {
        //Node current will point to head
        Bogie current = head.next, index = null;
        if(head == null) {
            return;
        }
        else {
            while(!current.isEngine) {
                //Node index will point to node next to current
                index = current.next;

                while(!index.isEngine) {
                    //If current node's data is greater than index's node data, swap the data between them
                    if(!current.isEngine && current.dist > index.dist) {
                        swap(current,index);
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }

    }
    void swap(Bogie left , Bogie right){
        Bogie temp = new Bogie();

        temp.dist= right.dist;
        temp.srcStn= right.srcStn;
        temp.destStn=right.destStn;

        right.destStn = left.destStn;
        right.srcStn = left.srcStn;
        right.dist= left.dist;

        left.destStn = temp.destStn;
        left.srcStn = temp.srcStn;
        left.dist= temp.dist;


    }
    public void print(Bogie tHead){
        if (tHead.isEngine) return;
        // print list of head node
        print(tHead.next);
        // After everything else is printed
        if(tHead.destStn != null )
            System.out.print(tHead.destStn+" ");
    }
   Bogie reverse(Bogie head) {
//        System.out.println("reverse called");
        Bogie eng = new Bogie();
        eng.isEngine=true;
        eng.next = head;
        eng.destStn="aman";

        Bogie prev = null;
        Bogie current = eng;
        while(current != null) {
            Bogie next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
//       System.out.println("reverse called "+ prev.destStn+" "+prev.isEngine);
        return prev.next;
//        print(prev.next);
    }

    public Bogie mergeTwoLists(Bogie list1,Bogie list2){
        if (list1.isEngine) return list2;
        if (list2.isEngine) return list1;


        if(list1.dist<list2.dist){
            list1.next=mergeTwoLists(list1.next,list2);
            return list1;
        }
        else{
            list2.next=mergeTwoLists(list1,list2.next);
            return list2;
        }


    }
    public int findDistance(String stn){return distanceMap.getOrDefault(stn,-1);}
    public Bogie rearrange() {
        Bogie newHead = new Bogie();
        ArrayList<String> order = copyOf();

        Map<String, LinkedList<Bogie>> bogieMap = new HashMap<>();
        Bogie temp = head;
        while (temp != null){
            if(bogieMap.containsKey(temp.destStn)){
                bogieMap.get(temp.destStn).add(temp);
            }else{
                LinkedList<Bogie> n = new LinkedList<>();
                n.add(temp);
                bogieMap.put(temp.destStn, n);
            }
            temp= temp.next;
        }

        temp = newHead;
        for (int i = order.size()-1 ; i >= 0; i--){
            String dest = order.get(i);


            LinkedList<Bogie> list = bogieMap.get(dest);

            if(list != null) {
                for (Bogie b : list) {
                    temp.next = b;
                    temp = temp.next;
                }
                bogieMap.remove(dest);
            }
            if(i==0) {
                temp.next = new Bogie();
                temp.next.isEngine = true;
            }
        }
        return newHead;

    }
    ArrayList<String> copyOf(){
        ArrayList<String > orderOfStation = new ArrayList<>();
        Bogie temp = head;
        while(!temp.isEngine){
            orderOfStation.add(temp.destStn);
            temp = temp.next;
        }
        return orderOfStation;
    }
    void fullFillDistances(Map<String,Integer> otherTrain){

        Bogie temp = head;
        while(!temp.isEngine){
            if(temp.dist == -1){
                int BPL_A = distanceMap.get("BPL");
                int BPL_B = otherTrain.get("BPL");
                int destDistFromB_BPL = otherTrain.getOrDefault(temp.destStn,0) - BPL_B;
                int actualDistanceFromA_BPL = BPL_A + destDistFromB_BPL;
                temp.dist=actualDistanceFromA_BPL;
            }
            temp=temp.next;
        }

    }
}
