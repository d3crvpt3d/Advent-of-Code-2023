import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;

        int pos_y = 0;

        char[][] array = new char[140][140];

        // fill array
        while (scanner.hasNextLine()) {

            array[pos_y++] = scanner.nextLine().toCharArray();

        }


        for (int y = 0; y < array.length; y++) {
            
            for (int x = 0; x < array[0].length; x++) {

                int a = -1;
                int b = -1;
                
                while (Character.isDigit(array[y][x])) {
                    if(a == -1){ a = x;} //included

                    b = x; //included
                    x++;
                    if(x == 140){
                        break;
                    }
                }

                if( a != -1 && alignesSymbol(a, b, array, y)){

                    System.err.println(Integer.parseInt(arrToString(a, b, array, y)));
                    sum += Integer.parseInt(arrToString(a, b, array, y));
                }
            }

        }

        System.out.println(sum);

        scanner.close();
    }
    


    public static String arrToString(int a, int b, char[][] array, int y){

        String s = "";

        for (int g = a; g <= b; g++) {

            s = s.concat(Character.toString(array[y][g]));

        }

        return s.trim(); // just to be safe
    }



    public static boolean alignesSymbol(int a, int b, char[][] array, int y){

        System.err.println(a+" "+b+" "+y);

        //top
        if(y != 0){
            for (int i = Math.max(0, a-1); i <= Math.min(139, b+1); i++) {
                if(array[y-1][i] != '.' && !Character.isDigit(array[y-1][i])){
                    return true;
                }
            }
        }

        //l
        if(array[y][Math.max(0, a-1)] != '.' && !Character.isDigit(array[y][Math.max(0, a-1)])){
            return true;
        }

        //r
        if(array[y][Math.min(139, b+1)] != '.' && !Character.isDigit(array[y][Math.min(139, b+1)])){
            return true;
        }

        //bot
        if(y != 139){
            for (int i = Math.max(0, a-1); i <= Math.min(139, b+1); i++) {
                if(array[y+1][i] != '.' && !Character.isDigit(array[y+1][i])){
                    return true;
                }
            }
        }

        return false;
    }
}