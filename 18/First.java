import java.util.ArrayList;
import java.util.Scanner;

public class First {

    private static ArrayList<int[]> values = new ArrayList<int[]>(); //X+-zahl, Y+-zahl, R, G, B

    public static void main(String[] args) {

        NiceTime nt = new NiceTime();
        
        Scanner sc = new Scanner(System.in);

        int[] firstPunkt = {0, 0};

        values.add(firstPunkt);

        int sumPath = 0;

        while (sc.hasNextLine()) {
            values.add(new int[2]);

            int[] ref = values.get(values.size()-1);
            
            String[] s = sc.nextLine().split("\\s+");

            sumPath += Integer.parseInt(s[1]);

            switch (s[0]) {
                case "L":
                    ref[0] = values.get(values.size()-2)[0]-Integer.parseInt(s[1]);
                    ref[1] = values.get(values.size()-2)[1];
                    break;
                case "R":
                    ref[0] = values.get(values.size()-2)[0]+Integer.parseInt(s[1]);
                    ref[1] = values.get(values.size()-2)[1];
                    break;
                case "U":
                    ref[0] = values.get(values.size()-2)[0];
                    ref[1] = values.get(values.size()-2)[1]-Integer.parseInt(s[1]);
                    break;
                case "D":
                    ref[0] = values.get(values.size()-2)[0];
                    ref[1] = values.get(values.size()-2)[1]+Integer.parseInt(s[1]);
                    break;
                default:
                    System.err.println("switch err");
                    break;
            }
        }
        sc.close();

        int output = 0;

        for (int i = 0; i < values.size()-1; i++) {

            output +=   values.get(i)[0]*values.get(i+1)[1]-
                        values.get(i)[1]*values.get(i+1)[0];
        }

        output +=   values.get(values.size()-1)[0]*values.get(0)[1]-
                    values.get(values.size()-1)[1]*values.get(0)[0];


        System.out.println(Math.abs(output/2)+sumPath/2+1);

        System.err.println(nt.getElapsedTime());
    }


}