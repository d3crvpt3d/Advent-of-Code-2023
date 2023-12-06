import java.util.Arrays;
import java.util.Scanner;

public class First {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        long[][] values = new long[2][4]; // 0 = time, 1 = distance

        int iteration = 0;

        while (scanner.hasNextLine()) {
            
            String line = scanner.nextLine();


            String[] tmp_line = line.split("\\s+");

            for (int i = 1; i < tmp_line.length; i++) { // start at 1, because of 'Time:' and 'Distance:'
                values[iteration][i-1] = Integer.parseInt(tmp_line[i]);
            }
            iteration++;
        }
        scanner.close();

        System.err.println(Arrays.toString(values[0]));

        System.err.println(Arrays.toString(values[1]));

        long output =    possibilities(values[0][0], values[1][0])*
                        possibilities(values[0][1], values[1][1])*
                        possibilities(values[0][2], values[1][2])*
                        possibilities(values[0][3], values[1][3]);

        System.out.println(output);
    }


    //calc distance for each possibility
    private static long possibilities(long t, long distance){

        long output = mitternachtsformel(-1, -1, t, -distance)-mitternachtsformel(1, -1, t, -distance);
        
        return output;
    }
    
    private static long mitternachtsformel(int type, long a, long b, long c){       
        
        return (long)( -1*b+(type*(Math.sqrt(b*b - 4*a*c))) ) /2*a;
    }
    
}