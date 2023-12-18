import java.util.ArrayList;
import java.util.Scanner;

public class Second {

    private static ArrayList<long[]> values = new ArrayList<long[]>(); //X+-zahl, Y+-zahl, R, G, B

    public static void main(String[] args) {

        NiceTime nt = new NiceTime();
        
        Scanner sc = new Scanner(System.in);

        long[] firstPunkt = {0, 0};

        values.add(firstPunkt);

        long sumPath = 0;

        while (sc.hasNextLine()) {
            values.add(new long[2]);

            long[] ref = values.get(values.size()-1);
            
            String[] s = sc.nextLine()  .replace("(#", "")
                                        .replace(")", "")
                                        .split("\\s+");

            
            switch (s[2].charAt(5)) {
                case '0':
                    s[0] = "R";
                    break;
                case '1':
                    s[0] = "D";
                    break;
                case '2':
                    s[0] = "L";
                    break;
                case '3':
                    s[0] = "U";
                    break;
                
                default:
                    break;
            }

            s[2] = s[2].substring(0, s[2].length()-1);

            sumPath += Long.parseLong(s[2], 16);

            switch (s[0]) {
                case "L":
                    ref[0] = values.get(values.size()-2)[0]-Long.parseLong(s[2], 16);
                    ref[1] = values.get(values.size()-2)[1];
                    break;
                case "R":
                    ref[0] = values.get(values.size()-2)[0]+Long.parseLong(s[2], 16);
                    ref[1] = values.get(values.size()-2)[1];
                    break;
                case "U":
                    ref[0] = values.get(values.size()-2)[0];
                    ref[1] = values.get(values.size()-2)[1]-Long.parseLong(s[2], 16);
                    break;
                case "D":
                    ref[0] = values.get(values.size()-2)[0];
                    ref[1] = values.get(values.size()-2)[1]+Long.parseLong(s[2], 16);
                    break;
                default:
                    System.err.println("switch err");
                    break;
            }
        }
        sc.close();

        long output = 0;

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