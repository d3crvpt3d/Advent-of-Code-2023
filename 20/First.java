import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

class First{

    static HashMap<String, Module> hMap = new HashMap<String, Module>();

    public static void main(String[] args){
        NiceTime nt = new NiceTime();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String[] s = sc.nextLine()
                        .replaceAll("->,", "")
                        .split("\\s+");
            
            switch (s[0].charAt(0)) {
                case '%':
                    hMap.put(s[0], new FlipFlop(s, hMap));
                    break;
                case '&':
                    hMap.put(s[0], new Conjunction(s, hMap));
                    break;
                default:
                    hMap.put(s[0], new Broadcaster(s, hMap));
                    break;
            }

        }
        sc.close();

        hMap.get("broadcaster").doThing();

        System.err.println(nt.getElapsedTime());
    }
}

interface Module{

    HashMap<String, Module> hMap = null; //TODO

    LinkedList<String> outputs = new LinkedList<String>();

    void doThing(boolean pulse, Module from);

}

class Broadcaster implements Module{

    public Broadcaster(String[] s, HashMap<String, Module> hMap){
        for (int i = 2; i < s.length; i++) {
            outputs.add(s[i]);
        }
    }

    @Override
    public void doThing(boolean pulse, Module from){
        for (String string : outputs) {
               hMap.get(string).doThing(pulse, this);
        }
    }

}

class Conjunction implements Module{

    private HashSet<Module> modSet = new HashSet<Module>();

    public Conjunction(String[] s, HashMap<String, Module> hMap){
        this.hMap = hMap;
        for (int i = 2; i < s.length; i++) {
            outputs.add(s[i]);
        }
    }

    @Override
    public void doThing(boolean pulse, Module from){



        for (String string : outputs) {
               hMap.get(string).doThing(pulse, this);//TODO
        }
    }
    
}

class FlipFlop implements Module{

    private boolean state = false;

    public FlipFlop(String[] s, HashMap<String, Module> hMap){
        for (int i = 2; i < s.length; i++) {
            outputs.add(s[i]);
        }
    }

    @Override
    public void doThing(boolean pulse){

        if(pulse){return;}
        state = !state;
        for (String string : outputs) {
               hMap.get(string).doThing(state);
        }
    }

}