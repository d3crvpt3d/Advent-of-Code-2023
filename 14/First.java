import java.util.Scanner;
import java.util.ArrayList;

class First{

    static ArrayList<ArrayList<Character>> aL = new ArrayList<ArrayList<Character>>();
    static ArrayList<Character> tmp = new ArrayList<Character>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){

            char[] cA = scan.nextLine().toCharArray();
            for (char c : cA) {
                tmp.add(c);
            }

            aL.add(tmp);
            tmp.clear();
        }
        scan.close();

        rollNorth();

        for (int a = 0; a < aL.size(); a++) {
            for(int b = 0; b < aL.get(a).size(); b++){
                System.err.print(aL.get(a).get(b));
            }
            System.err.println();
        }

        System.out.println(getWeightNorth());

    }

    private static void rollNorth(){
        
        for (int y = 0; y < aL.size(); y++) {
            ArrayList<Character> cL = aL.get(y);
            for(int x = 0; x < cL.size(); x++){
                if(cL.get(x) == 'O'){

                    int c = y;
                    while(  aL.get(c-1).get(x) != '#' ||
                            aL.get(c-1).get(x) != 'O'    ){
                        
                        if(--c == 1){
                            break;
                        }
                    }

                    cL.set(x, '.');
                    aL.get(y).set(x, 'O');

                }
            }
        }
    }

    private static int getWeightNorth(){
        return 0;
    }


}