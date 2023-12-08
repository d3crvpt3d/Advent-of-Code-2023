import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class First {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        //read instructions
        String line = scanner.nextLine();

        ArrayList<Integer> intructions = new ArrayList<Integer>(); // 0 == L, 1 == R

        for (int i = 0; i < line.length(); i++) {
            intructions.add(line.charAt(i) == 'L' ? 0 : 1);
        }


        int[][] network = new int[17576][2]; // size of 26*26*26+26*26+26 (ZZZ)

        scanner.nextLine(); // skip empty line

        while (scanner.hasNextLine()) {

            line = scanner.nextLine();

            network[getIntOfString(line.split("\\s+")[0])][0] = getIntOfString(line.split("\\s+")[2].replaceAll("[(|\\,]", "").trim());
            network[getIntOfString(line.split("\\s+")[0])][1] = getIntOfString(line.split("\\s+")[3].replaceAll("[)]", "").trim());

            //System.err.println(line.split("\\s+")[2].replaceAll("[(|\\,]", "").trim());
            //System.err.println(line.split("\\s+")[3].replaceAll("[)]", "").trim());

        }
        scanner.close();

        //System.err.println(Arrays.deepToString(network));

        long output = 0;
        int state = 0; // AAA

        while (state != 17575) {
            state = network[state][intructions.get((int) (output % (long)intructions.size()) )];
            output++;
        }


        System.out.println(output);

    }

    private static int getIntOfString(String s){

        int output = 0;
        int aVal = Character.getNumericValue('A');
        int max = 26;
        for (int i = 0; i < 3; i++) {
            output += (Character.getNumericValue(s.charAt(i))-aVal) * (Math.pow(max, i));
        }

        //System.err.println("Int of "+s+": "+output);

        return output;
    }

}
