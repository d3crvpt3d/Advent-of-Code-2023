import java.util.Scanner;
import java.util.ArrayList;
//import java.util.Arrays;

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

        int[] state = new int[6];

        state[0] = 47;
        state[1] = 565;
        state[2] = 549;
        state[3] = 411;
        state[4] = 0;
        state[5] = 497;

        int counter = 0;

        int[] state_old = new int[6];

        int[] state_b = new int[6];

        long[] state_length = new long[6];

        while (state[0] < 16900 || state[1] < 16900 || state[2] < 16900 ||  state[3] < 16900 || state[4] < 16900 || state[5] < 16900) {
            
            int instruc = intructions.get(counter);

            for (int index = 0; index < state.length; index++) {
                state[index] = network[state[index]][instruc];
            }

            output++;
            counter++;
            if(counter == intructions.size()){
                counter = 0;
            }

            for (int i = 0; i < 6; i++) {
                if(state[i] > 16900){
                    switch (state_b[i]) {
                        case 0: // first time
                            state_b[i]++;
                            state_old[i] = state[i]; // safe state of first time at xxZ
                            state_length[i] = output; // safe length of first time
                            break;
                        case 1: // second till end if loop
                            if(state[i] == state_old[i]){ // if loop at same state
                                state_b[i] = 2; // state[i] -> done
                            }else{ // if not loop
                                state_old[i] = state[i]; // safe state for next loop
                                state_length[i] = output;
                            }
                            break;
                            
                        default:
                            break;
                    }
                }
            }

            boolean end = true;
            for(int b : state_b){
                if(b != 2){
                    end = false;
                }
            }
            if (end) {
                break;
            }

        }

        output = 2; //TODO

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
