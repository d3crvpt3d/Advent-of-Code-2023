import java.util.ArrayList;
import java.util.Scanner;

class First{

    private static HashMap<String, Module> hMap = new HashMap<String, Module>();

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String[] s = sc.nextLine().split("\\s+");
            hMap.add( s[0], new Module(s));


        }
        sc.close();

    }
}

class Module{

    //create instructions etc;

    public Module(String[] s){
        //todo
    }




}
