import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Second {

    private static HashMap<String, Instruction> instructions = new HashMap<String, Instruction>();
    private static long[] xmas = {4000, 4000, 4000, 4000};

    public static void main(String[] args) throws InterruptedException {

        NiceTime nt = new NiceTime();
        
        Scanner sc = new Scanner(System.in);

        //Instructions
        while (sc.hasNextLine()) {
            String s1 = sc.nextLine();
            if(s1.isEmpty()){break;}
            String[] s = s1.split("[{,}]");

            instructions.put(s[0], new Instruction(s));
        }
        sc.close();

        long output = 0;

        propagate();

        System.out.println(output);

        System.err.println(nt.getElapsedTime());
    }

    private static void propagate(){

        recursive(instructions.get("in"), xmas);

    }

    private static long recursive(Instruction instruc, long[] xmas){



    }

}

class Range{

    ArrayList<Integer> start = new ArrayList<Integer>();
    ArrayList<Integer> end = new ArrayList<Integer>();

    private int size;

    public Range(int size){
        this.size = size;
    }

    public void addRange(int a, int b){
        
    }





}

class InstructionTuple{

    int key;
    int value;
    String operation;
    String returnValue;

    public InstructionTuple(String key, int value, String operation, String returnValue){
        switch (key) {
            case "x":
                this.key = 0;
                break;
            case "m":
                this.key = 1;
                break;
            case "a":
                this.key = 2;
                break;
            case "s":
                this.key = 3;
                break;
            default:
                System.err.println("key not x,m,a,s");
                break;
        }
        this.value = value;
        this.operation = operation;
        this.returnValue = returnValue;
    }
}

class Instruction{

    private ArrayList<InstructionTuple> tuples = new ArrayList<InstructionTuple>();
    private String then;

    public ArrayList<InstructionTuple> getTuples() {
        return tuples;
    }

    public String getThen() {
        return then;
    }

    public Instruction(String[] input){
        
        //create tuple for all input strings
        for (int i = 1; i < input.length-1; i++) {
            tuples.add(new InstructionTuple(
                input[i].substring(0,1),
                Integer.parseInt(input[i].split(":")[0].substring(2)),
                input[i].substring(1,2),
                input[i].split(":")[1]));
        }

        then = input[input.length-1];
    }

}
