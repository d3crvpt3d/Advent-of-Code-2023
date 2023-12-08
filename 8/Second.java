import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Second {
    
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

        long state1_old = 0;
        long state2_old = 0;
        long state3_old = 0;
        long state4_old = 0;
        long state5_old = 0;
        long state6_old = 0;

        //AAZ = 16900

        int state1 = 47;
        int state2 = 565;
        int state3 = 549;
        int state4 = 411;
        int state5 = 0;
        int state6 = 497;

        int counter = 0;

        boolean state1_b = false;
        boolean state2_b = false;
        boolean state3_b = false;
        boolean state4_b = false;
        boolean state5_b = false;
        boolean state6_b = false;

        while (state1 < 16900 || state2 < 16900 ||  state3 < 16900 || state4 < 16900 || state5 < 16900 || state6 < 16900) {
            
            int instruc = intructions.get(counter);
            state1 = network[state1][instruc];
            state2 = network[state2][instruc];
            state3 = network[state3][instruc];
            state4 = network[state4][instruc];
            state5 = network[state5][instruc];
            state6 = network[state6][instruc];
            output++;
            counter++;
            if(counter == intructions.size()){
                counter = 0;
            }

            if(state1 > 16900 && !state1_b){
                if(state1 == state1_old){
                    System.err.println("Loop1: "+state1_old);
                    state1_b = true;
                    state1_old = output;
                }else{
                    state1_old = state1;
                }
            }
            if(state2 > 16900 && !state2_b){
                if(state2 == state2_old){
                    System.err.println("Loop2: "+state2_old);
                    state2_b = true;
                    state2_old = output;
                }else{
                    state2_old = state2;
                }
            }
            if(state3 > 16900 && !state3_b){
                if(state3 == state3_old){
                    System.err.println("Loop3: "+state3_old);
                    state3_b = true;
                    state3_old = output;
                }else{
                    state3_old = state3;
                }
            }
            if(state4 > 16900 && !state4_b){
                if(state4 == state4_old){
                    System.err.println("Loop4: "+state4_old);
                    state4_b = true;
                    state4_old = output;
                }else{
                    state4_old = state4;
                }
            }
            if(state5 > 16900 && !state5_b){
                if(state5 == state5_old){
                    System.err.println("Loop5: "+state5_old);
                    state5_b = true;
                    state5_old = output;
                }else{
                    state5_old = state5;
                }
            }
            if(state6 > 16900 && !state6_b){
                if(state6 == state6_old){
                    System.err.println("Loop6: "+state6_old);
                    state6_b = true;
                    state6_old = output;
                }else{
                    state6_old = state6;
                }
            }

            if(state1_b && state2_b && state3_b && state4_b && state5_b && state6_b){
                break;
            }

        }

        output = state1_old*state2_old*state3_old*state4_old*state5_old*state6_old;

        System.err.println(state1_old);
        System.err.println(state2_old);
        System.err.println(state3_old);
        System.err.println(state4_old);
        System.err.println(state5_old);
        System.err.println(state6_old);

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
