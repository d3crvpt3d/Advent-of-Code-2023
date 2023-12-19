import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;

public class First {

    private static LinkedList<Item> itemList = new LinkedList<Item>();
    private static HashMap<String, Instruction> instructions = new HashMap<String, Instruction>();

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

        //Items
        while (sc.hasNextLine()) {
            String[] s2 = sc.nextLine()
                        .replace("{","")
                        .replace("}", "")
                        .split("[,=]");

            itemList.add(new Item(s2, instructions));

        }
        sc.close();

        //run all items parallel
        while (!itemList.isEmpty()) {
            itemList.pop().run();
        }

        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).join(0);
        }

        System.out.println(Item.getOutput());

        System.err.println(nt.getElapsedTime());
    }
}

class ItemTuple{

    int key;
    int value;

    public ItemTuple(String key, int value){
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

class Item extends Thread{

    private ItemTuple[] xmas = new ItemTuple[4];
    private HashMap<String, Instruction> instructions;
    private String retValue;
    private static int output = 0;

    public static int getOutput() {
        return output;
    }

    public static synchronized void addOutput(int x){
        output += x;
    }

    public Item(String[] st, HashMap<String, Instruction> instructions){
        for (int i = 0; i < xmas.length; i++) {
            xmas[i] = new ItemTuple(st[2*i], Integer.parseInt(st[2*i+1]));
        }

        this.instructions = instructions;
    }

    @Override
    public void run(){
        retValue = instructions.get("in").use(xmas);
        while (instructions.containsKey(retValue)) {
            retValue = instructions.get(retValue).use(xmas);
        }

        if(retValue.equals("A")){
            Item.addOutput( xmas[0].value+
                            xmas[1].value+
                            xmas[2].value+
                            xmas[3].value);
        }
    }

}

class Instruction{

    private ArrayList<InstructionTuple> tuples = new ArrayList<InstructionTuple>();
    private String then;

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

    public String use(ItemTuple[] xmas){
        
        for (int i = 0; i < tuples.size(); i++) {

            switch (tuples.get(i).operation) {
                case "<":
                    if (xmas[tuples.get(i).key].value < tuples.get(i).value) {
                        return tuples.get(i).returnValue;
                    }
                    break;
                case ">":
                    if (xmas[tuples.get(i).key].value > tuples.get(i).value) {
                        return tuples.get(i).returnValue;
                    }
                    break;
                default:
                    return "R";
            }
        }
        return then;
    }

}
