import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Scanner;

public class Second{

    private static int extra = 1_000_000;
    private static boolean[] filledZeiles;
    private static boolean[] filledSpalte;

    public static void main(String[] args) {

        ArrayList<ArrayList<Character>> space = new ArrayList<ArrayList<Character>>();

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()){

            ArrayList<Character> cArr = new ArrayList<Character>();

            for (char c : scanner.nextLine().toCharArray()) {
                cArr.add(c);
            }

            space.add(cArr);
        }
        scanner.close();


        filledZeiles = new boolean[space.size()];
        filledSpalte = new boolean[space.get(0).size()];

        //mark pos in extraDistanceZeile if void in Zeile
        fillExtraDistanceZeile(space, filledZeiles);

        //mark pos in extraDistanceZeile if void in Zeile
        fillExtraDistanceSpalte(space, filledSpalte);


        //DEBUG
//        System.err.println("Zeile: "+Arrays.toString(filledZeiles));
//        System.err.println("Spalte: "+Arrays.toString(filledSpalte));

        //fill galaxy List
        ArrayList<Galaxy> galaxyList = new ArrayList<Galaxy>();
        for (int y = 0; y < space.size(); y++) {
            ArrayList<Character> a = space.get(y);
            for (int x = 0; x < space.get(y).size(); x++) {
                char c = a.get(x);
                if(c == '#'){
                    galaxyList.add(new Galaxy(x, y, extra, filledZeiles, filledSpalte));
                }
            }
        }

        //binom loop to get paths to each galaxy
        for (int i = 0; i < galaxyList.size(); i++) {
            Galaxy g1 = galaxyList.get(i);
            for (int j = i+1; j < galaxyList.size(); j++) {
                g1.setB(galaxyList.get(j));
                g1.run();
            }
        }


        long out = 0;

        for (Galaxy galaxy : galaxyList) {
            out += galaxy.getPathLength();
        }

        System.out.println(out);

    }//end main
    

    private static void fillExtraDistanceZeile(ArrayList<ArrayList<Character>> sList, boolean[] bArr){

        for (int i = 0; i < sList.size(); i++) {
            ArrayList<Character> aL = sList.get(i);

            for (int j = 0; j < aL.size(); j++) {
                if(aL.get(j) == '#'){
                    bArr[i] = true;
                }
            }
        }
    }

    private static void fillExtraDistanceSpalte(ArrayList<ArrayList<Character>> sList, boolean[] bArr){
        
        for (int i = 0; i < sList.size(); i++) {
            ArrayList<Character> aL = sList.get(i);

            for (int j = 0; j < aL.size(); j++) {
                if(aL.get(j) == '#'){
                    bArr[j] = true;
                }
            }
        }

    }


    

}



class Galaxy implements Runnable{
    private int x;
    private int y;
    private long pathLength = 0;

    //multithreading
    private boolean[] filledZeiles;
    private boolean[] filledSpalte;
    private int extra;

    private Galaxy b;

    public Galaxy(int x, int y, int extra, boolean[] filledZeiles, boolean[] filledSpalte){
        this.x = x;
        this.y = y;
        
        this.extra = extra;
        this.filledZeiles = filledZeiles;
        this.filledSpalte = filledSpalte;
    }

    public void setB(Galaxy b) {
        this.b = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getPathLength() {
        return pathLength;
    }

    public void addPath(long a) {
        pathLength += a;
//        System.err.println("Added "+a+" to "+this);
    }

    @Override
    public void run() {
        this.pathLength();
    }

    private void pathLength(){

        int xmin;
        int xmax;

        int ymin;
        int ymax;

        if(this.getX() - b.getX() > 0){
            xmin = b.getX();
            xmax = this.getX();
        }else{
            xmin = this.getX();
            xmax = b.getX();
        }

        if(this.getY() - b.getY() > 0){
            ymin = b.getY();
            ymax = this.getY();
        }else{
            ymin = this.getY();
            ymax = b.getY();
        }

        int lücken = 0;

        //add count of lücken zwischen ax bx und ay by
        for (int x = xmin+1; x < xmax; x++) {
            if(!filledSpalte[x]){
                lücken++;
            }
        }
        //add count of lücken zwischen ax bx und ay by
        for (int y = ymin+1; y < ymax; y++) {
            if(!filledZeiles[y]){
                lücken++;
            }
        }

        this.pathLength +=  xmax - xmin +
                            ymax - ymin +
                            lücken * extra-
                            lücken;

    }
}