import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Second {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;

        int pos_y = 0;

        char[][] array = new char[140][140];

        ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(0);

        for(int i = 0; i < 144*144; i++){
            list.add(new LinkedList<Integer>());
        }

        System.err.println("Size "+list.size());

        

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

                if( a != -1 && alignesSymbol(a, b, array, y) != null){

                   //System.err.println(a+" "+b+" "+y);

                    list.get(alignesSymbol(a, b, array, y)[0]+140*alignesSymbol(a, b, array, y)[1]).add(Integer.parseInt(arrToString(a, b, array, y)));

                }
            }

        }

        for (LinkedList<Integer> llist : list) {

            if(llist.size() > 1){

                int ratio = 1;
                while (!llist.isEmpty()) {
                    ratio *= llist.pop();
                }

                sum += ratio;
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



    //returns coord of '*'
    public static int[] alignesSymbol(int a, int b, char[][] array, int y){

        int[] output = new int[2];

        //top
        if(y != 0){
            for (int i = Math.max(0, a-1); i <= Math.min(139, b+1); i++) {
                if(array[y-1][i] == '*'){
                    output[0] = y-1;
                    output[1] = i;
                    return output;
                }
            }
        }

        //l
        if(array[y][Math.max(0, a-1)] == '*'){
            output[0] = y;
            output[1] = Math.min(139, b+1);
            return output;
        }

        //r
        if(array[y][Math.min(139, b+1)] == '*'){
            output[0] = y;
            output[1] = Math.min(139, b+1);
            return output;
        }

        //bot
        if(y != 139){
            for (int i = Math.max(0, a-1); i <= Math.min(139, b+1); i++) {
                if(array[y+1][i] == '*'){
                    output[0] = y+1;
                    output[1] = i;
                    return output;
                }
            }
        }

        return null;
    }
}