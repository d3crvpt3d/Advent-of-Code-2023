import java.util.Scanner;

class First{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        scanner.useDelimiter(",");

        int output = 0;

        while(scanner.hasNext()){

            output += stringToHash(scanner.next());

        }

        scanner.close();

        System.out.println(output);
    }

    static private int stringToHash(String s){

        int o = 0;

        for(int i = 0; i < s.length(); i++){

            o += (int)s.charAt(i);
            o *= 17;
            o = o % 0x100;
        }


        return o;
    }

}