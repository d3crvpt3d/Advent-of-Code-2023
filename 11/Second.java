import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Scanner;

public class Second {

    private static long extra = 1_000_000;
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
                    galaxyList.add(new Galaxy(x, y));
                }
            }
        }

        //binom loop to get paths to each galaxy
        for (int i = 0; i < galaxyList.size(); i++) {
            Galaxy g1 = galaxyList.get(i);
            for (int j = i+1; j < galaxyList.size(); j++) {
                g1.addPath(pathLength(g1, galaxyList.get(j)));
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


    private static long pathLength(Galaxy a, Galaxy b){

        int xmin;
        int xmax;

        int ymin;
        int ymax;

        if(a.getX() - b.getX() > 0){
            xmin = b.getX();
            xmax = a.getX();
        }else{
            xmin = a.getX();
            xmax = b.getX();
        }

        if(a.getY() - b.getY() > 0){
            ymin = b.getY();
            ymax = a.getY();
        }else{
            ymin = a.getY();
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

        return  xmax - xmin +
                ymax - ymin +
                lücken * extra-
                lücken;

    }

}



class Galaxy{
    private int x;
    private int y;
    private long pathLength = 0;

    public Galaxy(int x, int y){
        this.x = x;
        this.y = y;
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
}