import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    private static int extra = 2;

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

        //DEBUG
        //print length of all paths from all galaxys
        galaxyList.forEach(i -> System.err.println(i.getPathLength()));
        //DEBUG

        int out = 0;

        for (Galaxy galaxy : galaxyList) {
            out += galaxy.getPathLength();
        }

        System.out.println(out);

    }//end main
    
    private static int pathLength(Galaxy a, Galaxy b){

        int lücken = 0;

        //add count of lücken zwischen ax bx und ay by
        //TODO



        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) + lücken * extra;

    }

}



class Galaxy{
    private int x;
    private int y;
    private int pathLength = 0;

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

    public int getPathLength() {
        return pathLength;
    }

    public void addPath(int a) {
        pathLength += a;
    }
}