import java.util.Scanner;

public class Second {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        long t = Long.parseLong(scanner.nextLine().split(":")[1].replace(" ", "").trim());
        long d = Long.parseLong(scanner.nextLine().split(":")[1].replace(" ", "").trim());
        scanner.close();
        
        System.out.println(possibilities(t, d));
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