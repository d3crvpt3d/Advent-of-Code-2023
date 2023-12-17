import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class First {

    static ArrayList<ArrayList<Knoten>> inputList = new ArrayList<ArrayList<Knoten>>();
    static PriorityQueue<Knoten> pQueue = new PriorityQueue<Knoten>();
    static HashSet<Knoten> check_set = new HashSet<Knoten>();

    public static void main(String[] args) {

        NiceTime nt = new NiceTime();

        Scanner sc = new Scanner(System.in);

        int curr_y = 0;
        while (sc.hasNextLine()) {
            
            inputList.add(new ArrayList<Knoten>());

            String s = sc.nextLine();

            for (int curr_x = 0; curr_x < s.length(); curr_x++) {
                Knoten k = new Knoten(curr_x, curr_y, Integer.parseInt(""+s.charAt(curr_x)));
                inputList.get(curr_y).add(k);
                check_set.add(k);
            }
            curr_y++;
        }
        sc.close();

        int size_x = inputList.get(0).size();
        int size_y = inputList.size();

        int wholeHeatCost = 0;


        //update cost to use euclidean distance and fill Queue
        for (int y = 0; y < inputList.size(); y++) {
            for(int x = 0; x < inputList.get(0).size(); x++){
                inputList.get(y).get(x).updateCost(size_x, size_y);
                pQueue.add(inputList.get(y).get(x));
            }
        }






        //trace path
        Knoten start = inputList.get(0).get(0);
        Knoten end = inputList.get(size_y).get(size_x);
        do {
            wholeHeatCost += start.getCost();
            start = start.getPrevKnoten();
        } while (!start.getPrevKnoten().equals(end));


        System.out.println(wholeHeatCost);

        System.out.println(nt.getElapsedTime());
    }

}

/**
 * Knoten
 */
class Knoten implements Comparable<Knoten>{

    private int cost;
    private int init_cost;
    private Knoten prevKnoten;
    private int x;
    private int y;
    private ArrayList<Knoten> neighbors = new ArrayList<Knoten>(4);

    public Knoten(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.init_cost = cost;
        this.cost = 0;
    }

    public int getInit_cost() {
        return init_cost;
    }

    public int getCost(){
        return cost;
    }

    public Knoten getPrevKnoten() {
        return prevKnoten;
    }

    public void updateCost(int size_x, int size_y){
        size_x = size_x-this.x;
        size_y = size_y-this.y;
        this.cost = this.init_cost+fastSqrt(size_x*size_x+size_y+size_y);
    }

    private int fastSqrt(int x){
        if( x <= 1){
            return x;
        }

        int x0 = x>>1;

        int x1 = (x0 + x / x0) >> 1;

        while (x1 < x0) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2;
        }
        return x0;
    }

    @Override
    public int compareTo(Knoten o) {
        return cost - o.cost;
    }

    
}